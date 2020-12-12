package com.zjj.Leetcode.Leetcode331;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"
        ));
    }

    public boolean isValidSerialization(String preorder) {
        String[] str = preorder.split(",");
        Deque<String> deque = new ArrayDeque<>();
        for (String s : str) {
            while (!deque.isEmpty()
                    && "#".equals(s)
                    && "#".equals(deque.peek())) {
                deque.pop();
                if (deque.isEmpty()) {
                    return false;
                }
                deque.pop();
            }
            deque.push(s);

        }
        return deque.size() == 1 && "#".equals(deque.peek());
    }
}
