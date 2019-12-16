package com.ZJJ.Leetcode.Leetcode232;

import java.util.Stack;

/**
 * leetcode #232.用栈实现队列
 * 每次push都从stackIn入栈，每次pop都从stackOut出栈
 */
public class StackQueue2 {
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;
    private Integer peekValue;

    public StackQueue2() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        if (stackIn.isEmpty()) {
            peekValue = x;
        }
        stackIn.push(x);
    }

    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek() {
        return stackOut.isEmpty() ? peekValue : stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}
