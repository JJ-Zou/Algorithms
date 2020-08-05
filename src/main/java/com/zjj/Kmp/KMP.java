package com.zjj.Kmp;

public class KMP {
    private static int kmp(String str1, String str2) {
        if (str1 == null || str1.length() == 0
                || str2 == null || str2.length() == 0 || str1.length() < str2.length()) {
            return -1;
        }
        int p1 = 0;
        int p2 = 0;
        int[] next = next(str2);
        while (p1 < str1.length() && p2 < str2.length()) {
            if (str1.charAt(p1) == str2.charAt(p2)) {
                p1++;
                p2++;
            } else if (next[p2] == -1) {
                p1++;
            } else {
                p2 = next[p2];
            }
        }
        return p2 == str2.length() ? p1 - p2 : -1;
    }

    private static int[] next(String str) {
        if (str.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length()];
        next[0] = -1;
        next[1] = 0;
        int cur = 2;
        int cmp = 0;
        while (cur < str.length()) {
            if (str.charAt(cur - 1) == str.charAt(cmp)) {
                next[cur++] = ++cmp;
            } else if (cmp > 0) {
                cmp = next[cmp];
            } else {
                next[cur++] = 0;
            }
        }
        return next;
    }
}
