package com.zjj.lock;

import java.util.concurrent.TimeUnit;

public class SyncTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (SyncTest.class) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (SyncTest.class) {
                System.out.println("h");
            }
        }).start();
    }
}
