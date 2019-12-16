package com.ZJJ.Leetcode.Leetcode28;

/**
 * KMP算法
 * 利用next数组，保证索引haystack的指针不回溯
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = getNext(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - j;
        }
        return -1;
    }

    private int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < needle.length() - 2) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (needle.charAt(i) != needle.charAt(j)) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
