package com.zz.Leetcode.Leetcode709;

public class Solution {
    public static void main(String[] args) {
        System.out.println(toLowerCase("hello"));
    }

    public static String toLowerCase(String str) {
        int diff = 'a' - 'A';
        int len = str.length();
        char[] res = new char[len];
        for (int i = 0; i < len; i++) {
            char cur = str.charAt(i);
            if (cur >= 'A' && cur <= 'Z') {
                res[i] = (char) (cur + diff);
            } else {
                res[i] = cur;
            }
        }
        return new String(res);
    }
}
