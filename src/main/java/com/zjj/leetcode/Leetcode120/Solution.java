package com.zjj.leetcode.Leetcode120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(solution.minimumTotal(triangle));
        System.out.println(solution.minimumTotal2(triangle));
    }

    /**
     * 空间复杂度O(n^2)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        int r = 0;
        int c = 0;
        for (List<Integer> list : triangle) {
            c = 0;
            for (int num : list) {
                dp[r][c++] = num;
            }
            r++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j == i) {
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                }
                if (i == n - 1) {
                    res = Math.min(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * 空间复杂度O(n)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        if (n == 1) {
            return triangle.get(0).get(0);
        }
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    dp[j] = dp[j - 1] + triangle.get(i).get(j);
                } else if (j == 0) {
                    dp[j] += triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(dp[j - 1] + triangle.get(i).get(j),
                            dp[j] + triangle.get(i).get(j));
                }
            }
        }
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }
}
