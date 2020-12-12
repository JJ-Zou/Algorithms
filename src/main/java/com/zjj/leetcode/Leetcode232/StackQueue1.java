package com.zjj.Leetcode.Leetcode232;

import java.util.Stack;

/**
 * leetcode #232.用栈实现队列
 * 保证每次pop时，弹出栈底元素
 * push和pop操作都在stack中进行
 */
public class StackQueue1 {
    private Stack<Integer> stack;
    private Stack<Integer> tempStack;
    private Integer peekValue;

    public StackQueue1() {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            peekValue = x;
        }
        stack.push(x);
    }

    public int pop() {
        while (stack.size() > 1) {
            peekValue = stack.pop();
            tempStack.push(peekValue);
        }
        int popValue = stack.pop();
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        return popValue;
    }

    public int peek() {
        return peekValue;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
