package com.zjj.leetcode.leetcode57;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 5}};
        int[] newInterval = new int[]{0, 0};
        System.out.println(Arrays.deepToString(new Solution().insert(intervals, newInterval)));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        TreeSet<Integer> set = new TreeSet<>();
        TreeMap<Integer, int[]> start = new TreeMap<>();
        TreeMap<Integer, int[]> end = new TreeMap<>();
        for (int[] interval : intervals) {
            set.add(interval[0]);
            set.add(interval[1]);
            start.put(interval[0], interval);
            end.put(interval[1], interval);
        }
        Map.Entry<Integer, int[]> startEntry = start.floorEntry(newInterval[0]);
        Map.Entry<Integer, int[]> endEntry = end.ceilingEntry(newInterval[1]);
        int startIndex = 0;
        int endIndex = 0;
        if (startEntry == null || newInterval[0]> startEntry.getValue()[1]) {
            startIndex = newInterval[0];
        } else {
            startIndex = startEntry.getValue()[0];
        }
        if (endEntry == null || newInterval[1] < endEntry.getValue()[0]) {
            endIndex = newInterval[1];
        } else {
            endIndex = endEntry.getValue()[1];
        }
        NavigableSet<Integer> subSet = set.subSet(startIndex, true, endIndex, true);
        if (!subSet.isEmpty()) {
            set.removeAll(new TreeSet<>(subSet));
        }
        set.add(startIndex);
        set.add(endIndex);
        int len = set.size() >>> 1;
        int[][] res = new int[len][2];
        int index = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            res[index][0] = iterator.next();
            res[index++][1] = iterator.next();
        }
        return res;
    }
}