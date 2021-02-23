package com.zjj.test.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ex0928 {
    public static void main(String[] args) {
//        int i = 0;
//        int sum = 0;
//        for (i = 3; i < 7; sum += i++) ;
//        System.out.println(sum);
//        System.out.println(new ex0928().getDigits(99123435078L));
//        System.out.println(new ex0928().getAmount(1));
//        System.out.println(new ex0928().getAmount(2));
//        System.out.println(new ex0928().getAmount(3));
//        System.out.println(new ex0928().getAmount(4));
//        System.out.println(new ex0928().getAmount(5));
//        System.out.println(new ex0928().getAmount(6));
//        System.out.println(new ex0928().getAmount(7));
        System.out.println(-1 / 5);
        System.out.println(-2 / 5);
        System.out.println(-3 / 5);
        System.out.println(-4 / 5);
        System.out.println(-8 / 5);

        AtomicInteger lock = new AtomicInteger(0);
        lock.incrementAndGet();
        Semaphore  semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    long getAmount(int m) {
        if (m < 3) {
            return 1;
        }
        if (m == 3) {
            return 2;
        }
        int[] dp = new int[m];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < m; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }
        return dp[m - 1];
    }

    long getDigits(long value) {
        String str = String.valueOf(value);
        int len = str.length();
        String s = "" + str.charAt(len - 3) + str.charAt(len - 5) + str.charAt(len - 7);
        long l = Long.parseLong(s);
        return l > 99 ? l : -1;
    }
}
