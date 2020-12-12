package com.zjj.Leetcode.Leetcode260;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int[] singleNumber(int[] nums) {
        int len = nums.length;
        if (len == 2) {
            return nums;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int mask = ((xor ^ (xor - 1)) >>> 1) + 1;
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & mask) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
