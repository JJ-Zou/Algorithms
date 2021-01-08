package com.zjj.leetcode.Leetcode165;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.compareVersion("1.0", "1.0.1"));
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int p1 = 0;
        int p2 = 0;
        while (p1 < len1 && p2 < len2) {
            int cmp = Integer.parseInt(v1[p1]) - Integer.parseInt(v2[p2]);
            if (cmp == 0) {
                p1++;
                p2++;
            } else if (cmp < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        if (p2 != len2) {
            while (p2 < len2) {
                if (Integer.parseInt(v2[p2]) > 0) {
                    return -1;
                }
                p2++;
            }
        }
        if (p1 != len1) {
            while (p1 < len1) {
                if (Integer.parseInt(v1[p1]) > 0) {
                    return 1;
                }
                p1++;
            }
        }
        return 0;
    }
}
