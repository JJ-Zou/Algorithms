package com.zjj.concurrencyInPractice.sharedObject;

/**
 * 未同步的情况下共享变量，不能保证主线程写入的ready和number值对读线程是可见的。
 * 如果发生指令重排序，可能会先写入ready
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
