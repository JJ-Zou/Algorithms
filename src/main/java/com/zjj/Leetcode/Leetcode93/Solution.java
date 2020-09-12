package com.zjj.Leetcode.Leetcode93;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses("101023"));
    }

    private List<String> res;

    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return null;
        }
        backtrack(s, s.length(), new ArrayDeque<>(), 0, 0);
        return res;
    }

    private void backtrack(String s, int len, Deque<String> deque, int segment, int start) {
        if (segment == 4 && start == len) {
            res.add(String.join(".", deque));
            return;
        }
        if(len - start > 12 - 3 * segment || len - start < 4 - segment) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (start + i >= len) {
                return;
            }
            String toCheck = s.substring(start, start + i + 1);
            if (valid(toCheck)) {
                deque.addLast(toCheck);
                backtrack(s, len, deque, segment + 1, start + i + 1);
                deque.pollLast();
            }
        }
    }

    private boolean valid(String toCheck) {
        if (toCheck.length() == 1) {
            return true;
        }
        if (toCheck.charAt(0) == '0') {
            return false;
        }
        return Integer.parseInt(toCheck) <= 255;
    }
}
