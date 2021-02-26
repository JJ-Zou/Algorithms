package com.zjj.somequestions.aqs;


/**
 * synchronized是可重入锁，拥有锁的线程再次进入同步代码块时会自动获得锁。
 * synchronized是利用字节码monitorenter和monitorexit进行加锁。
 * 每个锁对象拥有一个锁计数器和一个指向持有该锁线程的指针。当执行monitorenter时，
 * 如果目标锁对象计数器为0，则当前锁未被持有，该线程获得锁，并将计数器+1，指针指向
 * 当前线程。如果计数器不为0，只有拥有该锁的线程可以进去同步代码块，并将计数器加1，否则
 * 阻塞至锁释放。当执行monitorexit时，将锁对象计数器-1.
 */
public class SynchronizedLock {
    private Object object = new Object();

    public static void main(String[] args) {
        new SynchronizedLock().run();
    }

    public void run() {
        new Thread(() -> {
            synchronized (object) {
                System.out.println("enter reference: " + 1);
                synchronized (object) {
                    System.out.println("enter reference: " + 2);
                    synchronized (object) {
                        System.out.println("enter reference: " + 3);
                    }
                    System.out.println("exit reference: " + 2);
                }
                System.out.println("exit reference: " + 1);
            }
            System.out.println("exit reference: " + 0);
        }, "t1").start();
    }
}
