package com.zjj.leetcode.Leetcode29;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(-2147483648, -2));
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }
        if (divisor == -1) {
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        }
        int sym = (dividend >>> 31) ^ (divisor >>> 31);
        int one = (dividend == Integer.MIN_VALUE) ? Integer.MIN_VALUE
                : -Math.abs(dividend);
        int two = Math.abs(divisor);
        int res = 0;
        int t = two;
        int s = 1;
        // one += two;

        while (one + two <= 0) {
            one += two;
            res--;
            while (one + t < 0) {
                one += t;
                res -= s;
                t <<= 1;
                s <<= 1;
            }
        }
        return (sym == 0)
                ? (res == Integer.MIN_VALUE ? Integer.MAX_VALUE : -res)
                : res;
    }
}
