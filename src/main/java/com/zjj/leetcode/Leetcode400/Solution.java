package com.zjj.leetcode.Leetcode400;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findNthDigit(Integer.MAX_VALUE));
    }

    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        int bit = 1;
        long len = 9;
        while (n > len * bit) {
            n -= len * bit;
            bit++;
            len *= 10;
        }
        len /= 10;
        int num = (int) (n / bit + len / 9 * 10);
        int rank = n % bit;
        if (rank == 0) {
            String s = String.valueOf(num - 1);
            return s.charAt(s.length() - 1) - '0';
        }
        String s = String.valueOf(num);
        return s.charAt(rank - 1) - '0';
    }
}
