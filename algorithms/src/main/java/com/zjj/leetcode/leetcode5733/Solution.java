package com.zjj.leetcode.leetcode5733;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] rooms = {{2, 2}, {1, 2}, {3, 2}};
        int[][] queries = {{3, 1}, {3, 3}, {5, 2}};
        System.out.println(Arrays.toString(solution.closestRoom(rooms, queries)));
    }

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        Map<Integer, Integer> map = new HashMap<>();
        TreeMap<Integer, TreeSet<Integer>> sizeMap = new TreeMap<>(Comparator.comparingInt(o -> o));
        Arrays.stream(rooms).forEach(room -> {
            map.put(room[0], room[1]);
            sizeMap.computeIfAbsent(room[1], l -> new TreeSet<>())
                    .add(room[0]);
        });
        return Arrays.stream(queries).mapToInt(query -> {
            int preferred = query[0];
            int minSize = query[1];
            if (map.containsKey(query[0]) && map.get(query[0]) >= minSize) {
                return query[0];
            }
            return sizeMap.tailMap(minSize, true).values().stream()
                    .map(set -> {
                        Integer floor = set.floor(preferred);
                        if (floor != null) {
                            return floor;
                        }
                        return set.ceiling(preferred);
                    })
                    .min(((o1, o2) -> {
                        if (Math.abs(o1 - preferred) == Math.abs(o2 - preferred)) {
                            return o1 - o2;
                        }
                        return Math.abs(o1 - preferred) - Math.abs(o2 - preferred);
                    }))
                    .orElse(-1);
        }).toArray();
    }
}
