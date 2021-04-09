

#### IO模型

一个输入输出操作通常包括两个阶段：等待数据准备好；将数据从内核复制到进程。Unix有5种IO模型，阻塞式IO、非阻塞IO、IO多路复用、信号驱动IO、异步IO。

#### 阻塞IO

应用进程执行系统调用后被阻塞，直到数据从内核缓冲区复制到应用程序缓冲区才返回。在阻塞的过程中，其他进程可以执行，并不会消耗CPU时间，因此他的CPU利用率较高。

Java的标准输入是就是阻塞IO。

```java
import java.io.*;
import java.nio.CharBuffer;

public class BlockIO {
    public static void main(String[] args) throws IOException {
        CharBuffer buffer = CharBuffer.allocate(1024);
        InputStreamReader reader = new InputStreamReader(System.in);
        int read = reader.read(buffer);
        if (read > 0) {
            System.out.println(buffer.array());
        }
    }
}
```

#### 非阻塞IO

进程执行系统调用后，IO操作无法完成不会阻塞，而是返回一个错误码，需要轮询执行系统调用来获知IO是否完成。由于CPU要不断轮询调用系统调用，因此这种模型的CPU利用率比较低。

Java的NIO实际上是多路复用IO，可以使用c的非阻塞模式。

```c
#include <errno.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
int main() {
    int fd;
    fd = open("/dev/tty", O_RDONLY | O_NONBLOCK);
    char buf[1024];
    while(1) {
        int n = read(fd, buf, sizeof(buf));
        sleep(2);
        if(n <= 0) {
            printf("errno = %d\n", errno);
        } else {
            printf("buf = %s\n", buf);
            memset(buf, 0, 1024);
        }
    }
    return 0;
}
```

#### IO多路复用

使用select、poll、epoll等系统调用等待数据，并且可以等待多个socket中的任意一个变为可读，等待的过程会阻塞，当某个socket可读时返回，，使用recvfrom将数据从内核复制到进程。

IO多路复用可以让单进程/单线程具有处理多个IO的能力，不需要每一个socket都创建一个线程去处理，相比较于多线程和多进程，IO多路复用不需要进程线程的切换开销。

select：
```c
int select(int nfds, fd_set *readfds, fd_set *writefds,
           fd_set *exceptfds, struct timeval *timeout);
```
- 维护监听读操作readfds、写操作writefds、异常exceptfds的文件描述符列表，当被监听的文件描述符发生各自事件时，select返回并将就绪的描述符放入相应的描述符列表。
- select监听的文件描述符有上限，fd_set是一个bitmap，一般为1024大小
- 而在用户侧，select返回的是所有传入的监听列表，需要遍历整个监听列表取寻找就绪的文件描述符，使用完成后清理就绪状体再传入select进行下一轮操作
- 在内核侧，select每次陷入内核都需要遍历描述符集合，为其注册监听事件，从而在描述符ready时调用，而在select返回值之前，还需要再次遍历清除监听事件
- 无法动态添加描述符

poll：
```c
int poll(struct pollfd *fds, nfds_t nfds, int timeout);
struct pollfd {
               int   fd;         /* file descriptor */
               short events;     /* requested events */
               short revents;    /* returned events */
};
```
- poll与select类似
- 但是解决了select长度的问题，用nfds记录描述符数组的长度
- poll监听的事件如果没有发生变化可以重用，poll只会修改revents
- poll返回后仍然需要遍历寻找就绪的文件描述符，清理revents

epoll：
```c
int epoll_create(int size);
int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);
int epoll_wait(int epfd, struct epoll_event *events, int maxevents, int timeout);
typedef union epoll_data {
               void        *ptr;
               int          fd;
               uint32_t     u32;
               uint64_t     u64;
} epoll_data_t;
struct epoll_event {
               uint32_t     events;      /* Epoll events */
               epoll_data_t data;        /* User data variable */
};
```

**epoll步骤**

- 创建：epoll_create创建epoll描述符，创建一棵红黑树存放epoll_event
- 注册：epoll_ctl将一个需要监听的描述符以及监听的事件注册在epoll描述符上，向红黑树添加结点，时间复杂度为O(logN)；向内核注册回调函数，当一个socket数据到了，将网卡上的数据复制到内核中，再添加到就绪队列
- 获取就绪事件：执行epoll_wait等待被监听的描述符就绪，返回后遍历就绪的文件描述符表处理事件，时间复杂度为O(1)
- 当某个被监听的文件描述符不再需要时，通过epoll_ctl与epoll描述符解绑
- epoll需要主动close释放资源
- epoll红黑树，在epoll_ctl调用时根据传入的op区分删除、修改、添加操作

**epoll优点**

- 监听的描述符没有上限
- epoll_wait每次只返回就绪的描述符链表，不需要完整遍历整个监听列表
- 每次执行epoll_wait后不用重新配置监听，内核侧也不用反复注册和清理监听
- 可以使用epoll_ctl动态增减监听的描述符
- 即使没有线程调用epoll_wait，内核也能在描述符就绪时进行处理，等下次有线程调用epoll_wait时直接返回
- 多个线程可以同时调用epoll_wait

**epoll缺点**

- 每次修改监听事件调用epoll_ctl都会执行一次系统调用，没有批量操作方法，如果需要一次性监听很多描述符，比较耗时
- 对于大量连接反复连接断开处理效率低
- 会有惊群问题，多个进程/线程同时epoll_wait时，当文件描述符就绪后会被全部唤醒

**触发方式**

水平触发LT和边缘触发ET

- 水平触发：如果文件描述符的事件一直处于就绪状态，则每次调用epoll_wait都会立即返回。
- 边缘触发：当一个文件描述符就绪后，需要以非阻塞的方式一直操作这个文件描述符直到返回错误为止，期间这个就绪事件只会触发epoll_wait返回一次。

只在从rdlist返回时有区别，内核首先会将rdlist拷贝到一个临时链表，如果是LT事件并且事件就绪就重新放回rdlist，那么下次还会将rdlist的描述符返回给用户。

**对比**

- select超时精度为微妙，poll和epoll是毫秒，select更加适用于实时性要求比较高的场合
- select几乎被所有平台支持，移植性较好
- poll没有最大描述符数量限制
- 运行在Linux平台上有大量描述符需要轮询，并且是长连接时epoll性能最好。

```java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) {
        try (ServerSocketChannel socketChannel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {
            socketChannel.bind(new InetSocketAddress(20010), 1024);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int select = selector.select();
                System.out.println(select);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isAcceptable()) {
                        SocketChannel channel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = ((SocketChannel) selectionKey.channel()).read(buffer);
                        if (read > 0) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, read));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

上述代码运行后，在windows阻塞在poll，在Linux上阻塞在epoll_wait。

#### 信号驱动IO

通过使用sigaction系统调用，内核立即返回，应用程序可以继续执行，等待数据阶段是非阻塞的。内核在数据到达时发送信号，应用进程接收到信号后需要调用recvfrom将数据从内核复制到进程。

#### 异步IO

应用进程执行aio_read后立即返回，应用进程不会阻塞，内核会在完成数据复制后像进程发送信号。以上IO都是同步IO，在复制数据时都会阻塞，异步IO和信号驱动的区别在于，复制数据是内核完成的，而信号驱动IO需要自己从内核复制数据。

#### Java NIO

- Java中的NIO不是非阻塞IO，而是多路复用IO。
- 传统IO面向流，而NIO面向缓冲区，流是单向，每次只能从流出读取字节，而缓冲区可以缓存数据，通过通道Channel进行传输数据。Channel可以同时进行读写，而流分为输入流和输出流。
- Channel是数据传输的双向通道，可以用于读写。
- Buffer是数据缓冲区，可以进行读写切换的数组。
- Selector是检测Channel的组件，一个Selector可以注册到多个Channel上监听事件，可以使用一个线程处理多个请求。Selector使用多路复用技术实现，在windows上为poll，Linux上默认是epoll。
- NIO可以在native堆中分配直接内存，避免数据在Java堆和native来回复制，降低损耗。其中FileChannel.map使用内存映射文件mmap实现，将内核缓冲区的内存和用户缓冲区的内存做地址映射；FileChannel.transferTo通过sendfile直接将当前Channel的内容传输到另一个Channel而不经过Buffer。

#### Reactor模型

IO多路复用是Reactor的基础。主程序将事件及对应事件的处理方法注册到Reactor上，如果事件发生，进行回调，Reactor模型主要分为三个角色，Reactor用于将IO事件分发给对应的Handler处理，Acceptor用于处理客户端连接事件，Handler用于处理非阻塞的任务。

Reactor可以分为单线程、多线程和主从多线程。

- Reactor单线程：始终由一个线程负责分发事件、处理连接、读写事件和业务处理。仅使用一个线程处理请求没用充分利用多核CPU，当处理读写任务的负载过高时，事件会堆积，影响性能。
- Reactor多线程：将业务处理从单线程中隔离开来，使用线程池处理，提高性能。Reactor线程承担所有事件的处理，高并发下会存在性能问题。
- Reactor主从多线程：主线程只负责处理连接事件，从线程处理读写事件，业务处理交给线程池。处理连接一般只需要一个主线程，处理读写时间可以使用线程池。


