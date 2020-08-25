package com.zjj.Leetcode.Leetcode695;

/**
 * 逐个点DFS，将搜索过的1置为0
 */
public class MaxIsland {
    private int count;
    private int max;

    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                count = 0;
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                } else {
                    continue;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    private void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }
        grid[row][col] = 0;
        count++;
        dfs(grid, row + 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row - 1, col);
        dfs(grid, row, col - 1);
    }
}
