package com.zjj.leetcode.Leetcode917;

public class Solution {
    public String reverseOnlyLetters(String S) {
        char[] ch = S.toCharArray();
        int left = 0;
        int len = ch.length;
        int right = len - 1;
        while (left < right) {
            while (!Character.isAlphabetic(ch[left])) {
                left++;
                if (left >= right) {
                    return new String(ch);
                }
            }
            while (!Character.isAlphabetic(ch[right])) {
                right--;
                if (left >= right) {
                    return new String(ch);
                }
            }
            swap(ch, left++, right--);
        }
        return new String(ch);
    }

    private void swap(char[] ch, int a, int b) {
        char t = ch[a];
        ch[a] = ch[b];
        ch[b] = t;
    }
}
