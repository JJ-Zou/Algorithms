package com.zjj.leetcode.Leetcode388;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    public int lengthLongestPath(String input) {
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        int res = 0;
        int lastLevel = -1;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;
            if (level < lastLevel) {
                map = map.entrySet().stream()
                        .filter(m -> m.getKey() <= level)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            }
            lastLevel = level;
            s = s.replaceAll("\t+", "");
            int len = s.length();
            if (!map.containsKey(level)) {
                map.put(level, new ArrayDeque<>());
            }
            if (s.contains(".")) {
                int fileLen = level;
                for (int i = 0; i < level; i++) {
                    fileLen += map.get(i).peekLast();
                }
                res = Math.max(res, fileLen + len);
            } else {
                map.get(level).addLast(len);
            }
        }
        return res;
    }
}