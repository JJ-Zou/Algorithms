package com.zjj.leetcode.Leetcode139;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<String> wordDict = new ArrayList<>(
                Arrays.asList("leet", "code")
        );
        System.out.println(solution.wordBreak("leetcode", wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
