package com.ZJJ.Leetcode.Stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode #225.用队列实现栈
 * 用两个队列实现,在pop()操作中处理
 */
public class QueueStack1 {
    Queue<Integer> queueIn;
    Queue<Integer> queueOut;
    Integer topValue;
    public QueueStack1() {
        queueIn = new LinkedList<>();
        queueOut = new LinkedList<>();
    }

    public void push(int x){
        queueIn.add(x);
        topValue = x;
    }

    public int pop(){
        while(queueIn.size() > 1){
            topValue = queueIn.poll();
            queueOut.add(topValue);
        }
        Queue temp = queueIn;
        queueIn = queueOut;
        queueOut = temp;
        return queueOut.poll();
    }

    public int top(){
        return topValue;
    }

    public boolean empty(){
        return topValue == null;
    }
}
