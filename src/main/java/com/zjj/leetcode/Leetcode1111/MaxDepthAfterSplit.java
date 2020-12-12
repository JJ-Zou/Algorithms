package com.zjj.Leetcode.Leetcode1111;

public class MaxDepthAfterSplit {
    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        int d = 0;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                d++;
                if (d % 2 == 0) {
                    res[i] = 1;
                }
            } else {
                d--;
                if (d % 2 != 0) {
                    res[i] = 1;
                }
            }
        }
        return res;
    }
}
