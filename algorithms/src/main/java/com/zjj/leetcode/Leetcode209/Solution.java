package com.zjj.leetcode.Leetcode209;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = nums[0];
        int res = len + 1;
        while (left <= right) {
            while (right + 1 < len && sum < s) {
                sum += nums[++right];
            }
            if (sum >= s && right - left + 1 < res) {
                res = right - left + 1;
            }
            sum -= nums[left++];
        }
        return res == len + 1 ? 0 : res;
    }
}
