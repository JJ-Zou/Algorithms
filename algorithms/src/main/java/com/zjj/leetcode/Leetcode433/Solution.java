package com.zjj.leetcode.Leetcode433;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = Stream.of(bank).collect(Collectors.toSet());
        if (!set.contains(end)) {
            return -1;
        }
        int len = bank.length;
        Set<String> visited = new HashSet<>();
        char[] chs = {'A', 'C', 'G', 'T'};
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast(start);
        int res = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String cur = deque.pollFirst();
                if (cur.equals(end)) {
                    return res;
                }
                char[] ch = cur.toCharArray();
                for (int j = 0; j < 8; j++) {
                    char old = ch[j];
                    for (char c : chs) {
                        ch[j] = c;
                        String newStr = new String(ch);
                        if (!visited.contains(newStr) && set.contains(newStr)) {
                            visited.add(newStr);
                            deque.addLast(newStr);
                        }
                    }
                    ch[j] = old;
                }
            }
            res++;
        }
        return -1;
    }
}
