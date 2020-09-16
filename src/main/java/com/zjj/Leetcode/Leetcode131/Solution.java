package com.zjj.Leetcode.Leetcode131;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    private List<List<String>> res;
    private boolean[][] dp;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partition("abbab"));
    }

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        int len = s.length();
        if (len == 0) {
            return res;
        }
        dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        backtrack(s, 0, len, new ArrayDeque<>());
        return res;
    }

    private void backtrack(String s, int start, int length, Deque<String> deque) {
        if (start == length) {
            res.add(new ArrayList<>(deque));
            return;
        }
        for (int i = start; i < length; i++) {
            if (dp[start][i]) {
                deque.addLast(s.substring(start, i + 1));
                backtrack(s, i + 1, length, deque);
                deque.pollLast();
            }
        }
    }
}
