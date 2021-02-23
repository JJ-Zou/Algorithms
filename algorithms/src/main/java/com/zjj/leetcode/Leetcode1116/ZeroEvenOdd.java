package com.zjj.leetcode.Leetcode1116;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
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
    private Semaphore semaphore1;
    private Semaphore semaphore2;
    private Semaphore semaphore3;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.semaphore1 = new Semaphore(0);
        this.semaphore2 = new Semaphore(0);
        this.semaphore3 = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            printNumber.accept(0);
            ((i & 1) == 1 ? semaphore2 : semaphore3).release();
            semaphore1.acquire();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            semaphore2.acquire();
            printNumber.accept(i);
            semaphore1.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            semaphore3.acquire();
            printNumber.accept(i);
            semaphore1.release();
        }
    }
}
