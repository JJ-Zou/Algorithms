package com.zjj.leetcode.Leetcode1116;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    @SneakyThrows
    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(13);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(i -> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(i -> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(i -> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private int n;
    private Thread t1;
    private Thread t2;
    private Thread t3;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        t1 = Thread.currentThread();
        LockSupport.parkNanos(1000L);
        for(int i = 0; i < n; i++) {
            printNumber.accept(0);
            LockSupport.unpark((i & 1) == 1 ? t2 : t3);
            LockSupport.park();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        t2 = Thread.currentThread();
        for(int i = 2; i <= n; i+=2) {
            LockSupport.park();
            printNumber.accept(i);
            LockSupport.unpark(t1);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        t3 = Thread.currentThread();
        for(int i = 1; i <= n; i+=2) {
            LockSupport.park();
            printNumber.accept(i);
            LockSupport.unpark(t1);
        }
    }
}
