package com.zjj.leetcode.Leetcode213;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{2, 1}));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 1, len - 1), rob(nums, 0, len - 2));
    }

    public int rob(int[] nums, int start, int end) {
        int len = end - start + 1;
        if (len == 1) {
            return nums[start];
        }
        int[] dp = new int[len];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
        }
        return dp[len - 1];
    }
}
