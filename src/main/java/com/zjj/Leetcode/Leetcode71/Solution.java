package com.zjj.Leetcode.Leetcode71;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.simplifyPath("/home/ad/../"));
    }

    public String simplifyPath(String path) {
        String[] paths = path.substring(1).split("/");
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast("/");
        for(String p : paths) {
            if(".".equals(p)) {
                continue;
            } else if("..".equals(p)) {
                if(!deque.isEmpty() && !deque.peekLast().equals("/")) {
                    deque.pollLast();
                }
            } else {
                deque.addLast(p);
            }
        }
        return null;
    }
}
