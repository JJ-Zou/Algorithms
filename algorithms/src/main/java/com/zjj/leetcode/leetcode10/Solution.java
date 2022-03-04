package com.zjj.leetcode.leetcode10;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isMatch("aa", "a*");
    }
    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        if(lenS == 0 && lenP == 0) {
            return true;
        }
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[0][0] = true;
        for(int j = 2; j <= lenP; j++) {
            if(dp[0][j - 1]) {
                dp[0][j] = p.charAt(j - 1) == '*';
            } else {
                dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
            }
        }
        for(int i = 1; i <= lenS; i++) {
            for(int j = 1; j <= lenP; j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(j - 1) == '*') {
                    if(dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if(dp[i - 1][j]) {
                        dp[i][j] = s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.';
                    }
                }
            }
        }
        return dp[lenS][lenP];
    }
}