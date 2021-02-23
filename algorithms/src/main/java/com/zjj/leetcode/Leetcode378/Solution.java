package com.zjj.leetcode.Leetcode378;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = (left + right) >>> 1;
            int lowerNum = countMax(matrix, mid);
            if (lowerNum >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int countMax(int[][] matrix, int num) {
        int n = matrix.length;
        int r = n - 1;
        int c = 0;
        int count = 0;
        while (r >= 0 && c <= n - 1) {
            if (matrix[r][c] <= num) {
                c++;
                count += r + 1;
            } else {
                r--;
            }
        }
        return count;
    }
}
