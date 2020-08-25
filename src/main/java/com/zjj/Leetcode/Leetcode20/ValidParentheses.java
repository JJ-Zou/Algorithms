package com.zjj.Leetcode.Leetcode20;

import java.util.Stack;

/**
 * leetcode #20.有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，
 * 判断字符串是否有效。
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.length() % 2 != 0 || charStack.size() > s.length() / 2) {
                return false;
            }
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                charStack.push(s.charAt(i));
            } else if (!charStack.isEmpty() &&
                    ((s.charAt(i) == '}' && charStack.peek() == '{') ||
                            (s.charAt(i) == ')' && charStack.peek() == '(') ||
                            (s.charAt(i) == ']' && charStack.peek() == '['))) {
                charStack.pop();
            } else {
                return false;
            }
        }
        return charStack.isEmpty();
    }
}
