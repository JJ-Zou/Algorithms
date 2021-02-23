package com.zjj.leetcode.week227.question2;

public class Solution {
    public static void main(String[] args) {
        new Solution().maximumScore(6, 4, 8);
    }

    public int maximumScore(int a, int b, int c) {
        if (a > b) {
            return maximumScore(b, a, c);
        }
        if (a > c) {
            return maximumScore(c, b, a);
        }
        if (b > c) {
            return maximumScore(a, c, b);
        }
        System.out.printf("%d,%d,%d", a, b, c);
        return 0;
    }
}
