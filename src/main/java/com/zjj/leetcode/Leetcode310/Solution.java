package com.zjj.leetcode.Leetcode310;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMinHeightTrees(1,
                new int[][]{}));
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                deque.addLast(i);
            }
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int u = deque.pollFirst();
                res.add(u);
                for (int v : adj.get(u)) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        deque.addLast(v);
                    }
                }
            }
        }
        return res;
    }
}
