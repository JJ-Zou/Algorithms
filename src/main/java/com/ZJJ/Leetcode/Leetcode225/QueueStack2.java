package com.ZJJ.Leetcode.Leetcode225;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode #225.用队列实现栈
 * 用两个队列实现,在push(int x)操作中处理
 */
public class QueueStack2 {
    private Queue<Integer> queueIn;
    private Queue<Integer> queueOut;
    private Integer topValue;

    public QueueStack2() {
        queueIn = new LinkedList<>();
        queueOut = new LinkedList<>();
    }

    public void push(int x) {
        queueOut.add(x);
        topValue = x;
        while (!queueIn.isEmpty()) {
            queueOut.add(queueIn.poll());
        }
        Queue temp = queueIn;
        queueIn = queueOut;
        queueOut = temp;
    }

    public int pop() {
        int temp = queueIn.poll();
        if (!queueIn.isEmpty()) {
            topValue = queueIn.peek();
        } else {
            topValue = null;
        }
        return temp;
    }

    public int top() {
        return topValue;
    }

    public boolean empty() {
        return topValue == null;
    }
}
