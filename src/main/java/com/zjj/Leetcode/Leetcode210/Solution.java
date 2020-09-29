package com.zjj.Leetcode.Leetcode210;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }

    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] intoDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] prerequisity : prerequisites) {
            intoDegree[prerequisity[0]]++;
            adj.get(prerequisity[1]).add(prerequisity[0]);
        }
        int[] res = new int[numCourses];
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (intoDegree[i] == 0) {
                deque.addLast(i);
            }
        }
        while (!deque.isEmpty()) {
            int from = deque.pollLast();
            res[index] = from;
            index++;
            for (int to : adj.get(from)) {
                intoDegree[to]--;
                if (intoDegree[to] == 0) {
                    deque.addLast(to);
                }
            }
        }
        return index == numCourses ? res : new int[]{};
    }

    private List<List<Integer>> adj;
    private int[] visit;
    private boolean flag;
    private int[] res;
    private int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] prerequisity : prerequisites) {
            adj.get(prerequisity[1]).add(prerequisity[0]);
        }
        visit = new int[numCourses];
        res = new int[numCourses];
        index = numCourses;
        flag = true;
        for (int i = 0; i < numCourses && flag; i++) {
            if (visit[i] == 0) {
                dfs(i);
            }
        }
        return flag ? res : new int[]{};
    }

    private void dfs(int u) {
        if (!flag || visit[u] == 2) {
            return;
        }
        if (visit[u] == 1) {
            flag = false;
            return;
        }
        visit[u] = 1;
        for (int v : adj.get(u)) {
            dfs(v);
        }
        visit[u] = 2;
        res[--index] = u;
    }
}
