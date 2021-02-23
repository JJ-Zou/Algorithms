package com.zjj.leetcode.Leetcode207;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    private List<List<Integer>> adj;
    private int[] visit;
    private boolean result = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }
        visit = new int[numCourses];
        for (int i = 0; i < numCourses && result; i++) {
            if (visit[i] == 0) {
                dfs(i);
            }
        }
        return result;
    }

    private void dfs(int u) {
        if (!result) {
            return;
        }
        if (visit[u] == 1) {
            result = false;
            return;
        }
        visit[u] = 1;
        for (int v : adj.get(u)) {
            dfs(v);
        }
        visit[u] = 2;
    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] intoDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int[] prerequisity : prerequisites) {
            intoDegree[prerequisity[0]]++;
            adj.get(prerequisity[1]).add(prerequisity[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (intoDegree[i] == 0) {
                deque.addLast(i);
            }
        }
        while (!deque.isEmpty()) {
            int from = deque.pollFirst();
            numCourses--;
            for (int to : adj.get(from)) {
                intoDegree[to]--;
                if (intoDegree[to] == 0) {
                    deque.addLast(to);
                }
            }
        }
        return numCourses == 0;
    }
}
