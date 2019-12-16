package com.ZJJ.Leetcode.Leetcode27;

/**
 * 名为删除，实为填写与目标值不同的数
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
