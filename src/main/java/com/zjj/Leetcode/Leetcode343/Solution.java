package com.zjj.Leetcode.Leetcode343;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.integerBreak(1000));
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= (i >> 1); j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
