package com.zjj.test.leetcode.leetcode5;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.longestPalindrome("babadada");
        System.out.println(s);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        int chLen = 2 * len + 1;
        char[] ch = new char[chLen];
        for (int i = 0; i < len; i++) {
            ch[2 * i] = '#';
            ch[2 * i + 1] = s.charAt(i);
        }
        ch[2 * len] = '#';
        int[] max = new int[chLen];
        int cur = 0;
        int maxR = 0;
        int maxRight = -1;
        int maxIndex = 0;
        int lastMid = -1;
        int curR = 1;
        while (maxRight < chLen - 1) {
            if (cur > maxRight) {
                curR = 1;
                while (cur - curR >= 0 && cur + curR < chLen &&
                        ch[cur - curR] == ch[cur + curR]) {
                    curR++;
                }
                if (curR > maxR) {
                    maxR = curR;
                    maxIndex = cur;
                }
                if (maxRight < cur + curR - 1) {
                    lastMid = cur;
                    maxRight = cur + curR - 1;
                }
                max[cur] = curR;
            } else if (max[2 * lastMid - cur] == maxRight - cur + 1) {
                curR = maxRight - cur + 1;
                while (cur - curR >= 0 && cur + curR < chLen &&
                        ch[cur - curR] == ch[cur + curR]) {
                    curR++;
                }
                if (curR > maxR) {
                    maxR = curR;
                    maxIndex = cur;
                }
                if (maxRight < cur + curR - 1) {
                    lastMid = cur;
                    maxRight = cur + curR - 1;
                }
                max[cur] = curR;
            } else {
                max[cur] = Math.min(maxRight - cur + 1, max[2 * lastMid - cur]);
            }
            cur++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxIndex - maxR + 1; i <= maxIndex + maxR - 1; i++) {
            if (ch[i] != '#') {
                sb.append(ch[i]);
            }
        }
        return sb.toString();
    }
}
