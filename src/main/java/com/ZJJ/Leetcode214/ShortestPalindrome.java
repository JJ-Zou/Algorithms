package com.ZJJ.Leetcode214;

/**
 * 逆序马拉车
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        char[] ch = new char[2 * len + 1];
        ch[0] = '#';
        for (int i = 0; i < len; i++) {
            ch[2 * (i + 1) - 1] = s.charAt(len - 1 - i);
            ch[2 * (i + 1)] = '#';
        }
        len = 2 * len + 1;
        int[] max = new int[len];
        int cur = 0;
        int upset = 1;
        int maxRight = -1;
        int curMid = 0;
        while (maxRight < len - 1) {
            if (cur > maxRight || maxRight - cur + 1 == max[curMid - (cur - curMid)]) {
                upset = (cur > maxRight) ? 1 : maxRight - cur + 1;
                while (cur - upset >= 0 && cur + upset <= len - 1 && ch[cur - upset] == ch[cur + upset]) {
                    upset++;
                }
                max[cur] = upset;
                if (maxRight != cur + upset - 1) {
                    curMid = cur;
                    maxRight = cur + upset - 1;
                }
            } else {
                max[cur] = Math.min(maxRight - cur + 1, max[curMid - (cur - curMid)]);
            }
            cur++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= curMid - (len - 1 - curMid) - 1; i += 2) {
            sb.append(ch[i]);
        }
        sb.append(s);
        return sb.toString();
    }
}
