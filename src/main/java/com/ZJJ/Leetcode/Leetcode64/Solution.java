package com.ZJJ.Leetcode.Leetcode64;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0 + 0 * row);
        dp[0][0] = grid[0][0];
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int n = deque.pollFirst();
                int r = n % row;
                int c = n / row;
                if (r == row - 1 && c == col - 1) {
                    return dp[r][c];
                } else if (r == row - 1) {
                    dp[r][c + 1] = dp[r][c] + grid[r][c + 1];
                    if (i == size - 1) {
                        deque.addLast(r + (c + 1) * row);
                    }
                } else if (c == col - 1) {
                    dp[r + 1][c] = Math.min(dp[r + 1][c], dp[r][c] + grid[r + 1][c]);
                    deque.addLast(r + 1 + c * row);
                } else {
                    dp[r + 1][c] = Math.min(dp[r + 1][c], dp[r][c] + grid[r + 1][c]);
                    deque.addLast(r + 1 + c * row);
                    dp[r][c + 1] = dp[r][c] + grid[r][c + 1];
                    if (i == size - 1) {
                        deque.addLast(r + (c + 1) * row);
                    }
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }
}

