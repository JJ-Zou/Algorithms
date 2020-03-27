package com.ZJJ.Kmp;

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
        int k = -1;
        int cur = 0;
        while (cur < str.length() - 1) {
            if(k == -1 || str.charAt(cur) == str.charAt(k)) {
                k++;
                cur++;
                next[cur] = k;
            }else {
                k = next[k];
            }
        }
        return next;
    }
}
