package com.zjj.leetcode.Leetcode1519;

import java.util.*;

public class Solution {
    private Map<Integer, List<Integer>> map;
    private int[] res;
    private Map<Character, Integer> labelsMap;

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}};
        String labels = "cbabaa";
        System.out.println(Arrays.toString(new Solution().countSubTrees(n, edges, labels)));
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        map = new HashMap<>();
        res = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if (!map.containsKey(edges[i][0])) {
                map.put(edges[i][0], new ArrayList<>());
            }
            map.get(edges[i][0]).add(edges[i][1]);
        }
        dfs(0, labels);
        return res;
    }

    private void dfs(int vertex, String labels) {
        res[vertex]++;
        labelsMap = new HashMap<>();
        if (!map.containsKey(vertex)) {
            return;
        }
        for (int v : map.get(vertex)) {
            dfs(v, labels);
            labelsMap.put(labels.charAt(v), labelsMap.getOrDefault(labels.charAt(v), 0) + 1);
            res[v] = labelsMap.get(labels.charAt(v));
        }
    }
}
