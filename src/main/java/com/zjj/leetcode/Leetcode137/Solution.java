package com.zjj.Leetcode.Leetcode137;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.singleNumber(new int[]{2, 2, 2, 3, 3, 3, 88, 5545, 5454, 5545, 5454, 5454, 5545}));
    }

    public int singleNumber(int[] nums) {
        int n = nums.length;
        int bit1 = 0;
        int bit2 = 0;
        for (int num : nums) {
            int t = bit2;
            bit2 = (t ^ num) & (~bit1);
            bit1 = ((~num) & bit1 & (~t)) | (num & (~bit1) & t);
        }
        return bit2;
    }
}
