package com.zjj.Leetcode.Leetcode70;

/**
 * 斐波那契数列
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * dp[i] 表示爬到第i层有的方法数，
 * 1.在第i-1层时，爬到第i层有一种方法，
 * 2.在第i-2层时，爬到第i层有1种方法（另一种先爬到第i-1层包括在1中）
 * 则 dp[i] = dp[i-1]+dp[i-2]
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
