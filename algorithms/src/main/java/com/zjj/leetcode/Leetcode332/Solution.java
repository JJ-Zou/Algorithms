package com.zjj.leetcode.Leetcode332;

import java.util.*;

public class Solution {
    private Map<String, Queue<String>> map;
    private Deque<String> deque;

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        deque = new ArrayDeque<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)) {
                map.put(from, new PriorityQueue<>());
            }
            map.get(from).add(to);
        }
        dfs("JFK");
        return new ArrayList<>(deque);
    }

    private void dfs(String s) {
        while (map.containsKey(s) && !map.get(s).isEmpty()) {
            dfs(map.get(s).poll());
        }
        deque.addFirst(s);
    }
}
