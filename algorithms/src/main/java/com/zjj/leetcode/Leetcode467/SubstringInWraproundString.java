package com.zjj.leetcode.Leetcode467;

public class SubstringInWraproundString {
    public int findSubstringInWraproundString(String p) {
        if (p == null) {
            return 0;
        }
        int len = p.length();
        if (len <= 1) {
            return len;
        }
        int left = 1;
        int sum = 0;
        int[] word = new int[26];
        int count = 1;
        word[p.charAt(0) - 'a'] = 1;
        while (left < len) {
            if (p.charAt(left) - p.charAt(left - 1) == 1 || (p.charAt(left - 1) == 'z' && p.charAt(left) == 'a')) {
                count++;
            } else {
                count = 1;
            }
            word[p.charAt(left) - 'a'] = Math.max(word[p.charAt(left) - 'a'], count);
            left++;
        }
        for (int num : word) {
            sum += num;
        }
        return sum;
    }
}
