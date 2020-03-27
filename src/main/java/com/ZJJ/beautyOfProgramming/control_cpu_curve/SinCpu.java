package com.ZJJ.beautyOfProgramming.control_cpu_curve;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 让cpu曲线画出正弦图像
 */
public class SinCpu {
    public static void main(String[] args) {
        double f = 0.01;
        int N = (int) (1 / f);
        long[] busy = new long[N];
        long[] sleep = new long[N];
        int cpu = 4;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(cpu,
                cpu,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i < N; i++) {
            busy[i] = (long) (N * (1 + Math.sin(2 * Math.PI * i * f)));
            sleep[i] = 2 * N - busy[i];
        }
        for (int i = 0; i < cpu; i++) {
            poolExecutor.execute(() -> {
                int j = 0;
                while (true) {
                    j %= N;
                    long start = System.currentTimeMillis();
                    while ((System.currentTimeMillis() - start) <= busy[j]) ;
                    try {
                        Thread.sleep(sleep[j]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    j++;
                }
            });
        }
    }
}
