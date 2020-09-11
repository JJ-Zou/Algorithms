package com.zjj.Leetcode.Leetcode54;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution
                .spiralOrder(new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        {11,12,13,14,15,16,17,18,19,20}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int turn = (Math.min(m, n) + 1) >>> 1;
        for (int i = 0; i < turn; i++) {
            int r = i;
            int c = i;
            int up = i;
            int left = i;
            int down = m - 1 - i;
            int right = n - 1 - i;
            int count = (down == up && left == right) ? 1
                    : ((down == up) ? (right - left + 1)
                    : ((left == right) ? (down - up + 1)
                    : (down - up) * 2 + (right - left) * 2));
            while (count > 0) {
                res.add(matrix[r][c]);
                count--;
                if (r == up && c < right) {
                    c++;
                } else if (c == right && r < down) {
                    r++;
                } else if (r == down && c > left) {
                    c--;
                } else if (c == left && r > up) {
                    r--;
                }
            }
        }
        return res;
    }
}
