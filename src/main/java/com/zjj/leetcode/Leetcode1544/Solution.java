package com.zjj.leetcode.Leetcode1544;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.makeGood("NAanorRoOrROwnTNW"));
    }

    public String makeGood(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!deque.isEmpty() && Math.abs(deque.peekLast() - c) == 'a' - 'A') {
                deque.pollLast();
            } else {
                deque.addLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
