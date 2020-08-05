package com.zjj.beautyOfProgramming.control_cpu_curve;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  让cpu占用率固定在busy%
 */
public class LineCpu {
    public static void main(String[] args) {
        int cpu = 4;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(cpu,
                cpu,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>());
        long busy = 78;
        long sleep = 100 - busy;
        for (int i = 0; i < cpu; i++) {
            poolExecutor.execute(() -> {
                while (true) {
                    long start = System.currentTimeMillis();
                    while ((System.currentTimeMillis() - start) <= busy) ;
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
