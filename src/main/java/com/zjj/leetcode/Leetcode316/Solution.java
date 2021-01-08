package com.zjj.leetcode.Leetcode316;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateLetters("bbcaac"));
    }

    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        char[] ch = s.toCharArray();
        for (char c : ch) {
            count[c - 'a']++;
        }
        boolean[] indeque = new boolean[26];
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : ch) {
            count[c - 'a']--;
            if (indeque[c - 'a']) {
                continue;
            }
            while (!deque.isEmpty()
                    && c < deque.peekLast()
                    && count[deque.peekLast() - 'a'] > 0) {
                indeque[deque.pollLast() - 'a'] = false;
            }
            deque.addLast(c);
            indeque[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
