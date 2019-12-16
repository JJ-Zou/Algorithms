package com.ZJJ.Leetcode.Leetcode26;

/**
 * 快慢指针
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int index = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[++index] = nums[i + 1];
            }
        }
        return index + 1;
    }
}
