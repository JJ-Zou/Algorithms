package com.zjj.nowcoder.aiqiyi0822.ex4;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        final Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        new Thread(() -> {
            try {
                zeroEvenOdd.printZero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.printEven(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.printOdd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class ZeroEvenOdd {
    private int n;
    private Semaphore semaphoreZero;
    private Semaphore semaphoreEven;
    private Semaphore semaphoreOdd;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.semaphoreZero = new Semaphore(1);
        this.semaphoreEven = new Semaphore(0);
        this.semaphoreOdd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void printZero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphoreZero.acquire();
            printNumber.accept(0);
            if ((i & 1) == 1) {
                semaphoreOdd.release();
            } else {
                semaphoreEven.release();
            }
        }
    }

    public void printEven(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            semaphoreEven.acquire();
            printNumber.accept(i);
            semaphoreZero.release();
        }
    }

    public void printOdd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            semaphoreOdd.acquire();
            printNumber.accept(i);
            semaphoreZero.release();
        }
    }


}