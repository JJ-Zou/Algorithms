package com.ZJJ.Leetcode.Stack.Leetcode225;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode #225.用队列实现栈
 * 用一个队列实现
 */
public class QueueStack {
    private Queue<Integer> queue;

    public QueueStack() {
        queue = new LinkedList<>();
    }

    public void push(int x){
        queue.add(x);
        int count = queue.size();
        while(count > 1){
            queue.add(queue.poll());
            count--;
        }
    }

    public int pop(){
        return queue.poll();
    }

    public int top(){
        return queue.peek();
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
