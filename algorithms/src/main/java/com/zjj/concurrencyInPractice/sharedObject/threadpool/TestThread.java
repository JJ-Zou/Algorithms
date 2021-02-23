package com.zjj.concurrencyInPractice.sharedObject.threadpool;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Set<Thread> set = new HashSet<>();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            Thread thread = new Thread(() -> {
                if (index == 0) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(random.nextInt());
                set.add(Thread.currentThread());
                System.out.println(Thread.currentThread().getName());
            });
            thread.start();
        }
        System.out.println(set.size());
        System.out.println(list.size());
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
