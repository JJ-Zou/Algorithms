package com.zz.Leetcode.Leetcode443;

public class Solution {
    public static void main(String[] args) {
        char[] chars = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(new Solution().compress(chars));
    }

    public int compress(char[] chars) {
        int p = 0;
        int len = chars.length;
        int count = 0;
        char pre = ' ';
        int left = 0;
        while (p < len) {
            char cur = chars[p];
            if (pre != cur) {
                left = num2Str(chars, count, left);
                chars[left] = chars[p];
                count = 1;
                left++;
            } else {
                count++;
            }
            pre = cur;
            p++;
        }
        left = num2Str(chars, count, left);
        return left;
    }

    private int num2Str(char[] chars, int count, int left) {
        if (count > 1) {
            String s = String.valueOf(count);
            for (int i = 0; i < s.length(); i++) {
                chars[left++] = s.charAt(i);
            }
        }
        return left;
    }
}
