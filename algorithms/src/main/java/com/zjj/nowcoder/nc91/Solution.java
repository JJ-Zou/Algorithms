package com.zjj.nowcoder.nc91;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.LIS(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7})));
    }

    /**
     * retrun the longest increasing subsequence
     *
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     */
    public int[] LIS(int[] arr) {
        // write code here
        int len = arr.length;
        if (len == 0) {
            return new int[0];
        }
        int[] dp = new int[len];
        dp[0] = arr[0];
        int[] last = new int[len];
        last[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            if (arr[i] > dp[maxLen - 1]) {
                dp[maxLen++] = arr[i];
                last[i] = maxLen;
            } else {
                int index = binary(dp, maxLen - 1, arr[i]);
                dp[index] = arr[i];
                last[i] = index + 1;
            }
        }
        return last;
    }

    private int binary(int[] dp, int end, int target) {
        int left = 0;
        int right = end;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
