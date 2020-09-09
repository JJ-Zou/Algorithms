package com.zjj.Leetcode.Leetcode50;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow(2.000, 20));
    }

    public double myPow(double x, int n) {
        if (n == 0 || x == 1 || (x == -1 && (n & 1) == 0)) {
            return 1;
        }
        if (n == Integer.MIN_VALUE) {
            return 0;
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        double[] hashPow = new double[31];
        hashPow[0] = x;
        for (int i = 1; i < 31; i++) {
            hashPow[i] = hashPow[i - 1] * hashPow[i - 1];
        }
        double res = 1;
        for (int i = 0; i < 31; i++) {
            if (((n >>> i) & 1) == 1) {
                res *= hashPow[i];
            }
        }
        return res;
    }
}
