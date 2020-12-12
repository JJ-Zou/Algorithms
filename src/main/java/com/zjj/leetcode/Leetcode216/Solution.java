package com.zjj.Leetcode.Leetcode216;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    private List<List<Integer>> res;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum3(2, 43));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        dfs(k, n, 1, new ArrayDeque<>());
        return res;
    }

    private void dfs(int k, int n, int cur, Deque<Integer> deque) {
        if (n < 0) {
            return;
        }
        if (k == 0) {
            if (n == 0) {
                res.add(new ArrayList<>(deque));
                return;
            }
            return;
        }
        for (int i = cur; i <= 9; i++) {
            deque.addLast(i);
            dfs(k - 1, n - i, i + 1, deque);
            deque.pollLast();
        }
    }
}
