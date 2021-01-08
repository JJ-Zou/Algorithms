package com.zjj.leetcode.Leetcode56;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution
                .merge(new int[][]{{1, 4}, {2, 3}})));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Deque<int[]> deque = new ArrayDeque<>();
        for (int[] interval : intervals) {
            if (deque.isEmpty()) {
                deque.addLast(interval);
            } else {
                int[] last = deque.peekLast();
                if (interval[0] <= last[1]) {
                    deque.pollLast();
                    deque.addLast(new int[]{last[0], Math.max(last[1], interval[1])});
                } else {
                    deque.addLast(interval);
                }
            }
        }
        int[][] res = new int[deque.size()][2];
        for (int i = 0; i < res.length; i++) {
            int[] cur = deque.pollFirst();
            res[i][0] = cur[0];
            res[i][1] = cur[1];
        }
        return res;
    }
}
