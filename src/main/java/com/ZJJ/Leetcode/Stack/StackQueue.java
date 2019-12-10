package com.ZJJ.Leetcode.Stack;

import java.util.Stack;

/**
 * leetcode #232.用栈实现队列
 * 保证每次push时，push的元素都在栈底
 */
public class StackQueue {
    Stack<Integer> stack;
    Stack<Integer> tempStack;
    public StackQueue () {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }
    public void push(int x){
        while (!stack.isEmpty()){
            tempStack.push(stack.pop());
        }
        stack.push(x);
        while (!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }
    }

    public int pop(){
        return stack.pop();
    }

    public int peek(){
        return stack.peek();
    }
    public boolean empty(){
        return stack.isEmpty();
    }
}
