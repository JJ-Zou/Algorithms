package com.zjj.leetcode.Leetcode313;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.nthSuperUglyNumber(12,
                new int[]{2, 7, 13, 19}));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int k = primes.length;
        int[] pointers = new int[k];
        for (int i = 1; i < n; i++) {
            int min = primes[0] * dp[pointers[0]];
            for (int j = 1; j < k; j++) {
                min = Math.min(min, primes[j] * dp[pointers[j]]);
            }
            dp[i] = min;
            for (int j = 0; j < k; j++) {
                if (min == primes[j] * dp[pointers[j]]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n - 1];
    }
}
