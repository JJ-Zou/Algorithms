package com.zjj.leetcode.Leetcode376;

public class Solution {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int result = 1;
        int diff = 0;
        int prev = 0;
        for (int i = 1; i < len; i++) {
            int diffTemp = nums[i] - nums[prev];
            if (diffTemp != 0 && (diff == 0 || ((diffTemp ^ diff) >>> 31) == 1)) {
                result++;
                diff = diffTemp;
            }
            prev = i;
        }
        return result;
    }
}