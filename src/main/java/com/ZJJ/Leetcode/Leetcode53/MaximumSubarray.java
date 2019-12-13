package com.ZJJ.Leetcode.Leetcode53;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 动态规划，
 * dp[n-1] 代表到索引为 n-1 的数为止的，(包括nums[n-1]在内的最大自序和!!!!!)
 * 另外需要一个变量maxSum来存储最大自序和
 * 那么dp[n]的值由 nums[n] 来决定，
 * 如果dp[n-1]<0 且 nums[n]>dp[n-1]，dp[n]=nums[n]
 * 否则dp[n]=dp[n-1]+nums[n]
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return 0;
        }
        int maxSum = nums[0];
        int[] dp = new int[len];
        dp[0] = maxSum;
        for(int i=1;i<len;i++){
            if(dp[i-1]<0 && nums[i] > dp[i-1]) {
                dp[i] = nums[i];
            }else {
                dp[i] = dp[i-1]+nums[i];
            }
            maxSum = dp[i]>maxSum ? dp[i] : maxSum;
        }
        return maxSum;
    }
}
