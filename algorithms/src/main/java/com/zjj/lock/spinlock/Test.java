package com.zjj.lock.spinlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();
        Test test = new Test();
        int n = 1 << 26;
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                test.add(spinLock);
            }
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                test.minus(spinLock);
            }
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println(count);
    }

    private void add(SpinLock spinLock) {
        spinLock.lock();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        spinLock.unLock();
    }

    private void minus(SpinLock spinLock) {
        spinLock.lock();
        count--;
        spinLock.unLock();
    }
}
