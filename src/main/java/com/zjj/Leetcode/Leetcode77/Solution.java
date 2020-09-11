package com.zjj.Leetcode.Leetcode77;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combine(3,
                2));
    }

    private List<List<Integer>> res;
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        this.n = n;
        this.k = k;
        backtracking(new ArrayDeque<>(), 1);
        return res;
    }

    private void backtracking(Deque<Integer> deque, int index) {
        if (deque.size() == k) {
            res.add(new ArrayList<>(deque));
            return;
        }
        for (int i = index; i <= n; i++) {
            deque.addLast(i);
            backtracking(deque, i + 1);
            deque.pollLast();
        }
    }
}
