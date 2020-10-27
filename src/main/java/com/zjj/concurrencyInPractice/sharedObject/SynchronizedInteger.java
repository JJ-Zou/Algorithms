package com.zjj.concurrencyInPractice.sharedObject;

/**
 * 线程安全的可变整数访问器
 * synchronized加锁可以保证可见性和原子性，volatile只能保证可见性
 */
public class SynchronizedInteger {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
