package com.zz.Leetcode.Leetcode697;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 3, 1};
        System.out.println(findShortestSubArray(nums));
    }

    public static int findShortestSubArray(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        Queue<Integer> queue1 =
                new PriorityQueue<>((o1, o2) -> map.get(o2).size() - map.get(o1).size());
        queue1.addAll(map.keySet());
        Queue<Integer> queue2 =
                new PriorityQueue<>(Comparator.comparingInt(o -> (map.get(o).get(map.get(o).size() - 1) - map.get(o).get(0))));
        int degree = queue1.poll();
        queue2.add(degree);
        while (!queue1.isEmpty() &&
                map.get(queue1.peek()).size() == map.get(degree).size()) {
            queue2.add(queue1.poll());
        }
        return map.get(queue2.peek()).get(map.get(queue2.peek()).size() - 1) -
                map.get(queue2.peek()).get(0) + 1;
    }
}
