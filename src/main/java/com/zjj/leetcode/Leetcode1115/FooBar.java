package com.zjj.leetcode.Leetcode1115;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FooBar {
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private int n;
    private AtomicBoolean flag;
    private Thread t1;
    private Thread t2;

    public FooBar(int n) {
        this.n = n;
        this.flag = new AtomicBoolean(false);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        t1 = Thread.currentThread();
        for (int i = 0; i < n; i++) {
            if (flag.get()) {
                LockSupport.park();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            LockSupport.unpark(t2);
            flag.compareAndSet(false, true);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        t2 = Thread.currentThread();
        for (int i = 0; i < n; i++) {
            if (!flag.get()) {
                LockSupport.park();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            LockSupport.unpark(t1);
            flag.compareAndSet(true, false);
        }
    }
}
