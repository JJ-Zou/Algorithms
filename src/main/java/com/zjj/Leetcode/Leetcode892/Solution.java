package com.zjj.Leetcode.Leetcode892;

public class Solution {
    public static int surfaceArea(int[][] grid) {
        int N = grid.length;
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] > 0) {
                    area += 4 * grid[i][j] + 2;
                    if (i == 0 && j > 0) {
                        area -= 2 * Math.min(grid[i][j], grid[i][j - 1]);
                    } else if (j == 0 && i > 0) {
                        area -= 2 * Math.min(grid[i][j], grid[i - 1][j]);
                    } else if (i > 0) {
                        area -= 2 * Math.min(grid[i][j], grid[i][j - 1]);
                        area -= 2 * Math.min(grid[i][j], grid[i - 1][j]);
                    }
                }
            }
        }
        return area;
    }
}
