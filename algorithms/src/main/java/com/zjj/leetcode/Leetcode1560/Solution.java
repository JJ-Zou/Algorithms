package com.zjj.leetcode.Leetcode1560;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mostVisited(2,
                new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2}));
    }

    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] counts = new int[n];
        int m = rounds.length - 1;
        counts[rounds[0] - 1]++;
        for (int i = 0; i < m; i++) {
            for (int j = (rounds[i] % n); j != rounds[i + 1] - 1; j = (j + 1) % n) {
                counts[j]++;
            }
            counts[rounds[i + 1] - 1]++;
        }
        int max = counts[0];
        for (int count : counts) {
            max = Math.max(max, count);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (counts[i] == max) {
                list.add(i + 1);
            }
        }
        return list;
    }
}
