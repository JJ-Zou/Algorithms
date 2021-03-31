package com.zjj.leetcode.leetcode940;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().distinctSubseqII("zchmliaqdgvwncfatcfivphddpzjkgyygueikthqzyeeiebczqbqhdytkoawkehkbizdmcnilcjjlpoeoqqoqpswtqdpvszfaksn"));
    }

    public int distinctSubseqII(String S) {
        int len = S.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        int[] last = new int[26];
        int mod = 1000000007;
        for(int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1] * 2 % mod;
            if(last[S.charAt(i - 1) - 'a'] > 0) {
                dp[i] -= dp[last[S.charAt(i - 1) - 'a'] - 1];
                if(dp[i] < 0) {
                    dp[i] += mod;
                }
            }
            last[S.charAt(i - 1) - 'a'] = i;
        }
        return dp[len] - 1;
    }
}
