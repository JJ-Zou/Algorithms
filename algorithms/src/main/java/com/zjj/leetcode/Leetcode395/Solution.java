package com.zjj.leetcode.Leetcode395;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestSubstring("bbaaacddcaabdbd",
                3));
    }

    public int longestSubstring(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        char[] ch = s.toCharArray();
        int len = ch.length;
        for (char c : ch) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (map.get(ch[i]) < k) {
                deque.addLast(i);
            }
        }
        if (deque.isEmpty()) {
            return len;
        }
        deque.addLast(len);
        int left = 0;
        int right = 0;
        while (!deque.isEmpty()) {
            right = deque.pollFirst();
            if (right - left > res) {
                res = Math.max(res, longestSubstring(s.substring(left, right), k));
            }
            left = right + 1;
        }
        return res;
    }

    public int longestSubstringSlow(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (valid(ch, i, j, k)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    private boolean valid(char[] ch, int start, int end, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            map.put(ch[i], map.getOrDefault(ch[i], 0) + 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) < k) {
                return false;
            }
        }
        return true;
    }
}