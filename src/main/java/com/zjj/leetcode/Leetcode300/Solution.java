package com.zjj.leetcode.Leetcode300;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLIS0(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS0(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > dp[maxLen - 1]) {
                dp[maxLen] = nums[i];
                maxLen++;
            } else {
                int left = 0;
                int right = maxLen - 1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (nums[i] < dp[mid] && (mid == 0 || nums[i] > dp[mid - 1])) {
                        dp[mid] = nums[i];
                        break;
                    } else if (nums[i] < dp[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return maxLen;
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        int maxLen = 1;
        int res = 1;
        for (int i = 1; i < len; i++) {
            maxLen = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxLen = Math.max(dp[j] + 1, maxLen);
                }
            }
            dp[i] = maxLen;
            res = Math.max(res, maxLen);
        }
        return res;
    }
}
