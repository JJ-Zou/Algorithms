package com.zjj.leetcode.leetcode329;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{7,6,1,1},{2,7,6,0},{1,3,5,1},{6,6,3,2}};
        System.out.println(solution.longestIncreasingPath(matrix));
    }

    public int longestIncreasingPath(int[][] matrix) {
        int res = 1;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        boolean[][] visit = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int count = dfs(matrix, visit, i, j, m, n, dp);
                res = Math.max(res, count);
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, boolean[][] visit, int i, int j,
                    int m, int n, int[][] dp) {
        if(i < 0 || i >= m || j < 0 || j >= n || visit[i][j]) {
            return 0;
        }
        if(dp[i][j] > 0) {
            return dp[i][j];
        }
        visit[i][j] = true;
        int res = 1;
        if(i - 1 >= 0 && !visit[i - 1][j] && matrix[i - 1][j] > matrix[i][j]) {
            res = Math.max(res,
                    dfs(matrix, visit, i - 1, j, m, n, dp) + 1);
        }
        if(i + 1 < m && !visit[i + 1][j] && matrix[i + 1][j] > matrix[i][j]) {
            res = Math.max(res,
                    dfs(matrix, visit, i + 1, j, m, n, dp) + 1);
        }
        if(j - 1 >= 0 && !visit[i][j - 1] && matrix[i][j - 1] > matrix[i][j]) {
            res = Math.max(res,
                    dfs(matrix, visit, i, j - 1, m, n, dp) + 1);
        }
        if(j + 1 < n && !visit[i][j + 1] && matrix[i][j + 1] > matrix[i][j]) {
            res = Math.max(res,
                    dfs(matrix, visit, i, j + 1, m, n, dp) + 1);
        }
        visit[i][j] = false;
        dp[i][j] = Math.max(dp[i][j], res);
        return res;
    }

}