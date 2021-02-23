package com.zjj.leetcode.Leetcode840;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}};
        System.out.println(numMagicSquaresInside(grid));
    }

    public static int numMagicSquaresInside(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row - 2; i++) {
            for (int j = 0; j < col - 2; j++) {
                if (square(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean square(int[][] grid, int r, int c) {
        int i1 = grid[r][c];
        int i2 = grid[r][c + 1];
        int i3 = grid[r][c + 2];
        int i4 = grid[r + 1][c];
        int i5 = grid[r + 1][c + 1];
        int i6 = grid[r + 1][c + 2];
        int i7 = grid[r + 2][c];
        int i8 = grid[r + 2][c + 1];
        int i9 = grid[r + 2][c + 2];
        Set<Integer> set = new HashSet<>(Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9));
        if (set.size() != 9 || set.contains(0) || i1 > 9 || i2 > 9 || i3 > 9 || i4 > 9 || i5 > 9
                || i6 > 9 || i7 > 9 || i8 > 9 || i9 > 9) {
            return false;
        }
        int s1 = i1 + i2 + i3;
        int s2 = i4 + i5 + i6;
        int s3 = i7 + i8 + i9;
        int s4 = i1 + i4 + i7;
        int s5 = i2 + i5 + i8;
        int s6 = i3 + i6 + i9;
        int s7 = i1 + i5 + i9;
        int s8 = i3 + i5 + i7;
        return s1 == s2 && s2 == s3 && s3 == s4 && s4 == s5 && s5 == s6 &&
                s6 == s7 && s7 == s8;
    }
}
