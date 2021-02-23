package com.zjj.leetcode.Leetcode227;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate0(" 3/2 "));
    }

    public int calculate0(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char[] ch = s.toCharArray();
        int len = ch.length;
        int cur = 0;
        int num = 0;
        while (ch[cur] == ' ') {
            cur++;
        }
        while (cur < len && isNum(ch[cur])) {
            num = num * 10 + (ch[cur] - '0');
            cur++;
        }
        stack.push(num);
        while (cur < len) {
            if (ch[cur] == ' ') {
                cur++;
                continue;
            }
            if (!isNum(ch[cur])) {
                num = 0;
                char sym = ch[cur];
                cur++;
                while (ch[cur] == ' ') {
                    cur++;
                }
                while (cur < len && isNum(ch[cur])) {
                    num = num * 10 + (ch[cur] - '0');
                    cur++;
                }
                switch (sym) {
                    case '+':
                        break;
                    case '-':
                        num = -num;
                        break;
                    case '*':
                        num *= stack.pop();
                        break;
                    case '/':
                        num = stack.pop() / num;
                        break;
                }
                stack.push(num);
            }
        }
        int res = 0;
        for (int n : stack) {
            res += n;
        }
        return res;
    }

    public int calculate(String s) {
        Deque<String> stack = new ArrayDeque<>();
        int cur = 0;
        char[] ch = s.toCharArray();
        int len = ch.length;
        while (cur < len) {
            if (ch[cur] == ' ') {
                cur++;
                continue;
            }
            if (isNum(ch[cur])) {
                int num = ch[cur] - '0';
                cur++;
                while (cur < len && isNum(ch[cur])) {
                    num = num * 10 + (ch[cur] - '0');
                    cur++;
                }
                if (stack.isEmpty() || "+".equals(stack.peek()) || "-".equals(stack.peek())) {
                    stack.push(String.valueOf(num));
                } else {
                    String sym = stack.pop();
                    if ("*".equals(sym)) {
                        stack.push(String.valueOf(Integer.parseInt(stack.pop()) * num));
                    } else {
                        stack.push(String.valueOf(Integer.parseInt(stack.pop()) / num));
                    }
                }
            } else {
                stack.push(String.valueOf(ch[cur]));
                cur++;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            int t = Integer.parseInt(stack.pop());
            if (stack.isEmpty()) {
                return res + t;
            } else {
                if ("-".equals(stack.poll())) {
                    t = -t;
                }
                res += t;
            }
        }
        return res;
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
}
