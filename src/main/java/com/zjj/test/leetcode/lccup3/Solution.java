package com.zjj.test.leetcode.lccup3;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int minimumOperations(String leaves) {
        if(leaves == null || leaves.length() < 3) {
            return 0;
        }
        char[] ch = leaves.toCharArray();
        int len = ch.length;
        int res = 0;
        if(ch[0] == 'y' && ch[len - 1] == 'y') {
            res += 2;
            ch[0] = 'r';
            ch[len - 1] = 'r';
        } else if(ch[0] == 'y') {
            res += 1;
            ch[0] = 'r';
        } else if(ch[len - 1] == 'y') {
            res += 1;
            ch[len - 1] = 'r';
        }
        int count = 1;
        char cur = 'r';
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i < len; i++) {
            if(ch[i] == cur) {
                count++;
            } else {
                cur = (char) ('r' + 'y' - cur);
                deque.addLast(count);
                count = 1;
            }
        }
        if(deque.isEmpty()) {
            return 1 + res;
        }
        deque.pollFirst();
        int maxY = 0;
        int numY = 0;
        int numR = 0;
        boolean yflag = true;
        while(!deque.isEmpty()) {
            if(yflag) {
                int n = deque.pollLast();
                maxY = Math.max(maxY, n);
                numY += n;
                yflag = false;
            } else {
                numR += deque.pollLast();
                yflag = true;
            }
        }
        return Math.min(numY - maxY, numR) + res;
    }
}
