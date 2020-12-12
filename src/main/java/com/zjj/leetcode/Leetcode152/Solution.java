package com.zjj.Leetcode.Leetcode152;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, -2, 4, 4}));
    }

    /**
     * 优化
     *
     * @param nums
     * @return
     */
    public int maxProduct1(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int min = nums[0];
        int max = nums[0];
        int res = max;
        for (int i = 1; i < len; i++) {
            int t = max;
            max = Math.max(nums[i],
                    Math.max(nums[i] * t, nums[i] * min));
            min = Math.min(nums[i],
                    Math.min(nums[i] * t, nums[i] * min));
            res = Math.max(res, max);
        }
        return res;
    }

    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[][] dp = new int[len][2];
        dp[0][0] = dp[0][1] = nums[0];
        int res = dp[0][0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(nums[i],
                    Math.max(nums[i] * dp[i - 1][0], nums[i] * dp[i - 1][1]));
            dp[i][1] = Math.min(nums[i],
                    Math.min(nums[i] * dp[i - 1][0], nums[i] * dp[i - 1][1]));
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

}
