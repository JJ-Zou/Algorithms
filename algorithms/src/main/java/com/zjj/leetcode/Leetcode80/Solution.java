package com.zjj.leetcode.Leetcode80;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(
                new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int read = 0;
        int write = 0;
        int count = 1;
        while (read < n) {
            if (read > 0 && nums[read] == nums[read - 1]) {
                if (count >= 2) {
                    read++;
                } else {
                    nums[write] = nums[read];
                    read++;
                    write++;
                    count++;
                }
            } else {
                count = 1;
                nums[write] = nums[read];
                read++;
                write++;
            }
        }
        return write;
    }
}
