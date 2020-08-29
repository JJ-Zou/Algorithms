package com.zjj.tianchi0829.sub3;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shuttleInBuildings(new int[]{1, 5, 4, 3, 3, 5, 2}, 3, 10, 6));
    }

    public long shuttleInBuildings(int[] heights, int k, int x, int y) {
        // write your code here.
        int len = heights.length;
        int[] dp = new int[len];
        dp[0] = 0;
        if (heights[1] > heights[0] && x < y) {
            dp[1] = x;
        } else {
            dp[1] = y;
        }
        if(len == 2) {
            return dp[1];
        }
        int cur = 0;
        for(int i = cur; i < cur + k; i++) {
            for(int j = 0; j < k; j++) {
            }
        }
        return dp[len - 1];
    }
}
