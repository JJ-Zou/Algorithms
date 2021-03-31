package com.zjj.leetcode.leetcode1262;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSumDivThree(new int[]{2, 19, 6, 16, 5, 10, 7, 4, 11, 6}));
    }

    public int maxSumDivThree(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][3];
        dp[0][nums[0] % 3] = nums[0];
        for (int i = 1; i < len; i++) {
            int mod = nums[i] % 3;
            if (mod == 0) {
                dp[i][0] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][0]);
                if (dp[i - 1][1] != 0) {
                    dp[i][1] = Math.max(dp[i - 1][1] + nums[i], dp[i - 1][1]);
                }
                if (dp[i - 1][2] != 0) {
                    dp[i][2] = Math.max(dp[i - 1][2] + nums[i], dp[i - 1][2]);
                }
            } else if (mod == 1) {
                if (dp[i - 1][2] != 0) {
                    dp[i][0] = Math.max(dp[i - 1][2] + nums[i], dp[i - 1][0]);
                } else {
                    dp[i][0] = dp[i - 1][0];
                }
                dp[i][1] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][1]);
                if (dp[i - 1][1] != 0) {
                    dp[i][2] = Math.max(dp[i - 1][1] + nums[i], dp[i - 1][2]);
                } else {
                    dp[i][2] = dp[i - 1][2];
                }
            } else {
                if (dp[i - 1][1] != 0) {
                    dp[i][0] = Math.max(dp[i - 1][1] + nums[i], dp[i - 1][0]);
                } else {
                    dp[i][0] = dp[i - 1][0];
                }
                if (dp[i - 1][2] != 0) {
                    dp[i][1] = Math.max(dp[i - 1][2] + nums[i], dp[i - 1][1]);
                } else {
                    dp[i][1] = dp[i - 1][1];
                }
                dp[i][2] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][2]);
            }
        }
        return dp[len - 1][0];
    }
}