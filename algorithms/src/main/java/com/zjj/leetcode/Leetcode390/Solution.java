package com.zjj.leetcode.Leetcode390;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lastRemaining(1_000_000_000));
    }

    public int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        for (int i = n; i >= 1; i--) {
            stack2.addLast(i);
        }
        while (stack2.size() != 1) {
            stack2.pollLast();
            if (stack1.size() == 1) {
                return stack1.peekLast();
            }
            while (!stack2.isEmpty()) {
                stack1.addLast(stack2.pollLast());
                stack2.pollLast();
            }
            if (stack1.size() == 1) {
                return stack1.peekLast();
            }
            stack1.pollLast();
            if (stack1.size() == 1) {
                return stack1.peekLast();
            }
            while (!stack1.isEmpty()) {
                stack2.addLast(stack1.pollLast());
                stack1.pollLast();
            }
        }
        return stack2.peekLast();
    }
}