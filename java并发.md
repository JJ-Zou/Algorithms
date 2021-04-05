
#### java内存模型（JMM）

JMM定义了线程和主内存之间的抽象关系，线程间的共享变量存储在主内存中，每个线程有一个私有的本地内存，本地内存中存放提供给该线程读写的共享变量的副本。

- 共享变量存储在主内存，指的是实例变量和类变量；局部变量线程私有，不存在竞争
- 线程拥有本地内存，保存被线程使用的变量的副本
- 线程对变量的读写必须在本地工作内存中进行，不能直接读取主存中的数据
- 线程间不能直接访问对方的工作内存，变量的传递需要通过主内存

因此，线程对共享变量的修改不会立即更新到主内存中，或者主内存中的共享变量的最新值没能及时同步到工作内存中，从而导致线程使用的共享变量的值可能不是最新的。

#### volatile关键字

1、内存可见性

使用volatile修饰共享变量，线程要操作共享变量时，会从主存将最新值拷贝到本地内存作为副本，在操作完变量后，会将变量副本写回主内存，并通过CPU总线嗅探机制告知其他线程该变量副本已失效，需要从主存中重新读取。

volatile保证了不同线程对共享操作的可见性，当一个线程修改了volatile修饰的变量，当修改后的变量写回主内存时，其他线程能立即看到最新值。

每个处理器通过监听在总线上传播的数据来检查自己的缓存是否失效，当缓存行对应的内存地址被修改时，会重新从主存中把数据读到处理器缓存中。由于总线嗅探机制，会不停的监听总线，如果大量使用volatile会引起总线风暴。

2、有序性-禁止指令重排序

使用volatile修饰变量时，根据volatile重排序规则表，Java编译器在生成字节码时，会在指令序列中插入内存屏障指令来禁止特定类型的处理器重排序。

3、不保证原子性

volatile是无锁的，不能提供原子性和互斥性，但是volatile可以使纯赋值操作是原子的。

#### happens-before原则

- 程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。
- 监视器锁规则：对一个monitor的解锁，happen-before于随后对同一个monitor的加锁。
- volatile变量规则：对一个volatile变量的写，happens-before任意后续对这个volatile的读。
- 传递性：如果A happens-before B，且B happens-before C，那么A happens-before C。
- 线程start规则：Thread.start的调用happens-before于启动线程里的动作。
- 线程join规则：线程中的所有动作都happens-before于其他线程从该线程Thread.join返回。

#### synchronized特性

synchronized底层使用操作系统的mutex lock实现

1、原子性

被synchronized修饰的类或对象的所有操作都是原子的，在执行操作之前必须先获得类或对象的锁，直到执行完才能释放锁。

2、可见性

线程加锁前必须清空工作内存共享变量的值，从主存中获取共享变量的值，线程解锁前必须把共享变量刷新到主存。线程的同步确保当一个线程修改对象状态后，其他线程能够看到该变化。

3、有序性

synchronized保证了每个时刻都只有一个线程访问同步块代码，从而确保在同步块中线程执行具有先后顺序，从而保证有序性。

#### synchronized锁实现

synchronized有两种加锁方式，对方法加锁或者构造同步代码块。在进入同步代码之前需要先获得锁，获得锁后锁的计数器+1，执行完退出同步代码后释放锁，锁的计数器-1（可重入锁），如果获取锁失败线程阻塞等待锁释放。

- 同步方法：方法标志ACC_SYNCHRONIZED，告知JVM该方法为同步方法。
- 同步代码块：通过monitorenter指令进入，monitorexit指令退出，包括发生异常时，也会又monitorexit退出。

JVM中对象实例分为对象头、实例数据、对齐填充，实例数据存放类的数据信息包括父类的属性信息，如果是数组还包括数组的长度，JVM要求对象起始地址必须是8字节的整数倍，所以需要对齐填充。

synchronized实现方式于Java对象头密切相关。对象头主要由mark word和class metadata address组成，mark word存放对象的hashcode、锁状态、分代年龄、GC标志等信息，class metadata address是类型指针指向对象的类元数据，JVM以此判定对象是哪个类的实例。

#### synchronized优化

通过对synchronized优化，提升了其性能。

- 锁膨胀：锁会有四种状态，会根据实际情况依次升级，锁升级的方向不可逆。偏向锁和轻量级锁都是乐观锁，而重量级锁是悲观锁。
  - 无锁
  - 偏向锁：减少同一个线程获取锁的代价，在大多情况下不存在锁竞争，仅在第一次获取锁时通过CAS竞争锁，记录偏向的线程ID，后续只需比较线程ID，一旦存在竞争会撤销偏向锁并升级为轻量级锁，撤销偏向锁会导致stop the world（等待全局安全点）。
  - 轻量级锁：在多个线程进行锁竞争时，通过自旋等待占有锁的线程释放锁，自旋超过一定次数后，膨胀为重量级锁。适应性自旋，自旋次数由前一个在同一个锁上的自旋时间及锁的拥有者的状态来说决定，刚刚自旋成功获得锁，则允许自旋等待更多次数，反之。
  - 重量级锁：重量级锁由操作系统的互斥锁mutex实现，线程间的切换需要从用户态转换到核心态。
- 锁消除：JIT编译时通过逃逸分析技术，判断一段代码中数据不会逃逸出当前线程，则可以认为这段代码是线程安全的，可以将锁消除。
- 锁粗化：如果一系列操作对同一个对象反复加锁，甚至在循环体中反复加锁解锁，频繁发生线程切换，会导致不必要的性能损失，如果虚拟机检测到一串零碎的对同一对象的加锁解锁，将会把锁的范围粗化到整个操作序列的外部。

#### ReentrantLock原理

ReentrantLock底层采用CAS和AQS队列实现。

CAS是一种无锁的原子操作，提供对变量的预期值和欲修改的新值，只有当预期值与实际值相等时才会修改成功，否则失败返回。

AQS是一个先入先出（FIFO，CLH）的队列，用来存放等待获得锁的线程，队列的头结点为哨兵结点不与任何线程关联，其余节点均关联一个线并维护一个等待状态。

ReentrantLock非公平锁加锁过程：
- 首先通过CAS尝试获取锁（尝试修改AQS的同步状态state），如果成功，标记该线程获得独占锁，并返回。
- 再次获取AQS的同步状态，如果（state == 0）没有线程占有锁，通过CAS尝试获取锁，如果成功，标记该线程获得独占锁，并返回。
- 如果失败说明锁被占用，判断占用锁的线程是否是该线程本身，如果是，锁状态+1（可重入锁）。
- 否则分装为Node通过CAS操作加入AQS队列尾部，如果此时AQS中只有该线程（初始化哨兵结点时）尝试再次获得锁，成功则返回。
- 否则将该Node的结点wait状态设为SIGNAl，并使用LockSupport.park()挂起当前线程。
- 释放锁时，锁状态state--，并通过其是否为零判断锁是否完全释放（可重入锁）。

非公平锁从尝试加锁开始会多次尝试获得锁，如果在这期间成功获得了锁，就可以跳过在队列中的排队步骤（唤醒队列中下一个结点的时候允许其他线程竞争锁）。而公平锁唤醒下一个结点时，不允许其他线程竞争锁，新来的线程只能够通过插入队尾等待被唤醒。

#### synchronized和ReentrantLock的区别

- synchronized是Java内置的关键字，功能由JVM内置实现，而ReentrantLock是Java中的一个类
- synchronized在线程发生异常时，会自动根据异常表跳转释放锁，不会发生异常死锁，而Lock需要手动处理异常的解锁，通常在finally中释放锁
- synchronized是非公平锁，而Lock可以选择非公平锁或公平锁
- synchronized锁不可中断，而Lock提供可以响应中断的锁
- synchronized底层是互斥锁是悲观锁，而Lock基于乐观锁CAS和AQS队列实现，他们在线程等待释放锁时，线程的状态也不相同，synchronized是BLOCKED状态，而Lock是Waiting或TIMED_WAINTING状态。


#### 线程池ThreadPoolExecutor

线程池七大参数
```java
public ThreadPoolExecutor(int corePoolSize, //核心线程数
                          int maximumPoolSize, //最大线程数
                          long keepAliveTime, //非核心线程空闲时存活的时间
                          TimeUnit unit, //存活时间单位
                          BlockingQueue<Runnable> workQueue,//存放任务的阻塞队列
                          ThreadFactory threadFactory,//创建线程的线程工厂
                          RejectedExecutorHandler handler)//最大线程和工作队列满时的拒绝策略，默认为中止抛出异常
 {
}
```

线程池执行流程

- execute提交任务
- 当核心线程池未满时，将task包装为Worker执行该线程，线程Worker在run方法中不断循环从workQueue中取出task并执行；
- 核心线程满时，如果阻塞队列未满，将task加入阻塞队列排队等待执行；
- 如果阻塞队列已满，task数量不超过最大线程数，将创建非核心线程用于执行task；
- 否则，将任务提交到拒绝策略处理。

四种拒绝策略

- AbortPolicy（默认）：抛出一个RejectedExecutionException异常
- CallerRunsPolicy：将task交给当前线程处理
- DiscardPolicy：不进行任何处理，直接丢弃任务
- DiscardOldestPolicy：丢弃阻塞队列中最老的任务（阻塞队列头部poll），线程池再次提交该任务

#### ThreadLocal

ThreadLocal是线程局部变量，用于实现线程的数据隔离。线程Thread内有一个ThreadLocalMap变量，用于为每个线程绑定一个ThreadLocalMap，使用ThreadLocal获取本地变量时，通过线程获得ThreadLocalMap对象，从而对本地变量进行操作。

ThreadLocalMap是ThreadLocal的内部类哈希表，非集合框架，哈希表的键是一个指向ThreadLocal（this）的弱引用，值是ThreadLocal存储的数据，他使用线性探测来解决哈希冲突。当发生垃圾回收，弱引用被清理掉后，key会被清理为null，而value仍然是一个强引用，如果不进行手动清理会导致内存泄漏，因此在不需要使用ThreadLocal后需要调用remove方法进行清理，避免内存泄露。

同一个线程可以拥有多个ThreadLocal变量，作为ThreadLocalMap的键。

#### CountDownLatch

CountDownLatch可以使多个线程等待各自执行完毕后，再继续执行。CountDownLatch基于AQS实现。

- 创建计数器：将计数器的值赋给AQS的state变量
- await()阻塞线程：创建一个SHARED结点加入AQS队列，并将当前线程挂起（park）
- countDown()计数器递减：对计数器进行-1操作，当state=0时，唤醒AQS队列中所有挂起的线程

CountDownLatch是一次性的，不可重复使用。

#### CyclicBarrier

CyclicBarrier同步屏障，可以让一组线程达到一个屏障时被阻塞，直到最后一个线程达到屏障时，所有被阻塞的线程才能继续执行。CyclicBarrier基于ReentrantLock和Condition实现。

- 创建计数器：可以提供一个Runnable，由最后一个达到的线程执行的
- await()等待：计数器-1，使用Condition.await挂起当前线程并释放锁，当计数器为0时，执行传入的Runnable，刷新屏障，最终Condition.signalAll唤醒所有等待的线程。

CyclicBarrier可重复使用，当屏障被打破后，会重新刷新屏障。

#### Semaphore

Semaphore信号量，用来控制同时访问共享资源的线程数量。基于AQS实现。

- 创建许可证数量：默认是非公平的，将初始许可证数目赋值给AQS的state变量，代表剩余的许可证数目
- acquire获取许可证：获取许可证，state-1获取许可证，如果-1后的state<0或CAS竞争失败，创建一个SHARED结点加入AQS队列，并将当前线程挂起
- release释放许可证：通过自旋CAS执行state+1，成功后唤醒AQS所有挂起的线程，被唤醒的线程会重新去竞争许可证


