package com.zjj.somequestions.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * 1.LockSupport线程阻塞唤醒无锁
 * 2.如果unpark()在park()前执行，那么park()不生效
 */
public class ParkAndUnpack {
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " enter");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " wake up");
        }, "A");
        threadA.start();
        new Thread(() -> {
            LockSupport.unpark(threadA);
            System.out.println(Thread.currentThread().getName() + " notify");
        }, "B").start();
    }
}
