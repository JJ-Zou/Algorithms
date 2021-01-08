package com.zjj.leetcode.Leetcode1356;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{8191};
        System.out.println(Arrays.toString(solution.sortByBits(arr)));
    }

    public int[] sortByBits(int[] arr) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int j : arr) {
            int count = bitCount(j);
            if (!map.containsKey(count)) {
                map.put(count, new PriorityQueue<>());
            }
            map.get(count).add(j);
        }
        int[] res = new int[arr.length];
        int cur = 0;
        for (int i = 0; i <= 13; i++) {
            if (map.containsKey(i)) {
                Queue<Integer> queue = map.get(i);
                while (!queue.isEmpty()) {
                    res[cur++] = queue.poll();
                }
            }
        }
        return res;
    }

    private int bitCount(int n) {
        n = n - ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = n + (n >>> 8);
        n = n + (n >>> 16);
        return n & 0x3f;
    }
}
