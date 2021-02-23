package com.zjj.leetcode.Leetcode304;

public class Solution {
    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{{-4, -5}});
        System.out.println(numMatrix.sumRegion(0, 0, 0, 1));
    }

    static class NumMatrix {
        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            dp = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j] + matrix[i][j] - dp[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (dp == null) {
                return 0;
            }
            return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
        }
    }
}
