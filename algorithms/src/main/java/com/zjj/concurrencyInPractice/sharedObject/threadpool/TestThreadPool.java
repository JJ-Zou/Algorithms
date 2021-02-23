package com.zjj.concurrencyInPractice.sharedObject.threadpool;

import java.util.Deque;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
        Set<Thread> set = ConcurrentHashMap.newKeySet();
        Random random = new Random();
        Deque<Integer> list = new ConcurrentLinkedDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            threadPoolExecutor.submit(() -> {
                list.add(random.nextInt());
                set.add(Thread.currentThread());
            });
        }
        threadPoolExecutor.shutdown();
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(list.size());
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
