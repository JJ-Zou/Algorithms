package com.zjj.somequestions.aqs;

/**
 * 1.wait()和notify()必须在synchronized代码块中使用
 * 2.wait()让线程进入等待状态，同时会释放当前线程持有的锁，直到其他线程调用此对象的notify()方法。
 * 3.如果notify()在wait()方法之前执行，notify信号可能会丢失。
 * 4.ReentrantLock使用Condition的await()和signal()方法
 */
public class WaitAndNotify {
    private static Object object = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " enter");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wake up");
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (object) {
                object.notify();
                System.out.println(Thread.currentThread().getName() + " notify");
            }
        }, "B").start();
    }
}
