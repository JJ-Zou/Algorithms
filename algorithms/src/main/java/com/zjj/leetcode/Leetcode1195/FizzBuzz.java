package com.zjj.leetcode.Leetcode1195;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private Semaphore f;
    private Semaphore b;
    private Semaphore fb;
    private Semaphore num;
    public FizzBuzz(int n) {
        this.n = n;
        f = new Semaphore(0);
        b = new Semaphore(0);
        fb = new Semaphore(0);
        num = new Semaphore(0);
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(100);
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(i -> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i % 3) == 0 && (i % 5) != 0) {
                f.acquire();
                printFizz.run();
                num.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i % 3) != 0 && (i % 5) == 0) {
                b.acquire();
                printBuzz.run();
                num.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i % 3) == 0 && (i % 5) == 0) {
                fb.acquire();
                printFizzBuzz.run();
                num.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i % 3) != 0 && (i % 5) != 0) {
                printNumber.accept(i);
            } else if ((i % 3) == 0 && (i % 5) == 0) {
                fb.release();
                num.acquire();
            } else if ((i % 3) == 0) {
                f.release();
                num.acquire();
            } else {
                b.release();
                num.acquire();
            }
        }
    }
}