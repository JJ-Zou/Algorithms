package com.zjj.Leetcode.Leetcode31;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nextPermutation(new int[]{1, 3, 2});
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int right = len - 1;
        while (right > 0 && nums[right] <= nums[right - 1]) {
            right--;
        }
        if (right == 0) {
            Arrays.sort(nums);
            return;
        }
        int cur = right - 1;
        int minGreaterCur = len - 1;
        while (nums[minGreaterCur] <= nums[cur]) {
            minGreaterCur--;
        }
        int t = nums[minGreaterCur];
        nums[minGreaterCur] = nums[cur];
        nums[cur] = t;
        Arrays.sort(nums, right, len);
    }
}
