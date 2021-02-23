package com.zjj.leetcode.Leetcode396;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxRotateFunction(new int[]{1}));
    }

    public int maxRotateFunction(int[] A) {
        int n = A.length;
        long maxDelta = 0;
        int f0 = 0;
        int baseDelta = 0;
        long prefixDelta = 0;
        for (int i = 0; i < n; i++) {
            f0 += i * A[i];
            baseDelta += A[i];
        }
        for (int i = 0; i < n; i++) {
            prefixDelta += baseDelta - n * A[n - 1 - i];
            maxDelta = Math.max(maxDelta, prefixDelta);
        }
        return (int) (f0 + maxDelta);
    }
}