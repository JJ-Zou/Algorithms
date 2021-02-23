package com.zjj.leetcode.Leetcode189;

public class Solution {
    public static void main(String[] args) {
        rotate(new int[]{1, 2}, 3);
    }

    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        if (k >= len) {
            rotate(nums, k % len);
            return;
        }
        if (len == 1 || k == 0) {
            return;
        }
        reverse(nums, 0, len - 1 - k);
        reverse(nums, len - k, len - 1);
        reverse(nums, 0, len - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        int l = start;
        int r = end;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}