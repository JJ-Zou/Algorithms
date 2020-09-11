package com.zjj.Leetcode.Leetcode71;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.simplifyPath("/home/ad/../"));
    }

    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        for(String p : paths) {
            if (".".equals(p) || "".equals(p)) {
                continue;
            } else if ("..".equals(p)) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else {
                deque.addLast(p);
            }
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String p : deque) {
            sb.append('/').append(p);
        }
        return sb.toString();
    }
}
