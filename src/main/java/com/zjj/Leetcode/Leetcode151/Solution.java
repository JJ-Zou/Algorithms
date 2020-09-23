package com.zjj.Leetcode.Leetcode151;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public String reverseWords(String s) {
        String[] strs = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        int len = strs.length;
        for (int i = len - 1; i >= 0; i--) {
            sb.append(strs[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

}
