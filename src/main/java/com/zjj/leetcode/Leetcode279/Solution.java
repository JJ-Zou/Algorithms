package com.zjj.leetcode.Leetcode279;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(15));
    }

    public int numSquares(int n) {
        int sqrt = (int) Math.sqrt(n);
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (i + 1 == ((int) Math.sqrt(i + 1)) * ((int) Math.sqrt(i + 1))) {
                dp[i] = 1;
            }
            for (int j = 1; j <= sqrt && i - 1 + j * j < n; j++) {
                int k = i - 1 + j * j;
                if (dp[k] == 0 || dp[i - 1] + 1 < dp[k]) {
                    dp[k] = dp[i - 1] + 1;
                }
            }
        }
        return dp[n - 1];
    }
}
