package com.zjj.leetcode.Leetcode179;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestNumber(new int[]{2, 3, 2, 2, 4, 4}));
    }

    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if ("0".equals(strs[0])) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

}
