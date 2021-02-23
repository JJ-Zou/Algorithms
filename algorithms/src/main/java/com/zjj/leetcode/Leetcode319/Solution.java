package com.zjj.leetcode.Leetcode319;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.bulbSwitch(999999));
    }

    public int bulbSwitch0(int n) {
        return (int) Math.sqrt(n);
    }

    public int bulbSwitch(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 1;
        int b = 0;
        for (int i = 1; i < n; i++) {
            b = a + (factorNum(i + 1) ? 1 : 0);
            a = b;
        }
        return b;
    }

    private boolean factorNum(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
