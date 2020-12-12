package com.zjj.Leetcode.Leetcode868;

public class Solution {
    public static void main(String[] args) {
    }


    public int binaryGap(int N) {
        if ((N & (N - 1)) == 0) {
            return 0;
        }
        int cur = N;
        int count = 1;
        int max = -1;
        while (cur != 0) {
            if ((cur & 1) == 1) {
                if (max == -1) {
                    count = -1;
                }
                max = Math.max(max, count + 1);
                count = 0;
            } else {
                count++;
            }
            cur >>>= 1;
        }
        return max;
    }

}
