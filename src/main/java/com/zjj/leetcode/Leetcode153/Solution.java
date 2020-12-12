package com.zjj.Leetcode.Leetcode153;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(new int[]{2, 3, -2, -1, 0, 1}));
    }

    public int findMin(int[] nums) {
        int len = nums.length;
        if (nums[0] <= nums[len - 1]) {
            return nums[0];
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            }
        }
        return nums[left];
    }
}
