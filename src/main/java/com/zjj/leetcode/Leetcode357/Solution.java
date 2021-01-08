package com.zjj.leetcode.Leetcode357;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countNumbersWithUniqueDigits(0x1ffffff));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int sum = dp[0];
        int num = 9;
        int i = 1;
        for (; i <= n && num >= 0; i++) {
            dp[i] = sum + dp[i - 1] * (num--);
            sum += dp[i];
        }
        return dp[i - 1];
    }
}
