package com.ZJJ.Leetcode.Stack;

import java.util.Stack;

/**
 * leetcode #155.最小栈
 * 设计一个支持push,pop,top操作，并且能在常数时间内检索到最小元素的栈。
 * push(x)--入栈
 * pop()--出栈
 * top()--获取栈顶元素
 * getMin()--检索栈中最小元素
 */
public class MinStack {
    private Stack<Integer> stackValue;
    private Stack<Integer> stackMin;
    private int size;

    public MinStack() {
        stackValue = new Stack<>();
        stackMin = new Stack<>();
    }

    /**
     * 当栈为空时入栈元素为最小元素
     * 当栈不为空时，当前栈最小元素为min(stackMin栈顶元素,入栈元素)
     * @param value
     */
    public void push(int value){
        stackValue.push(value);
        if(size == 0 || value < stackMin.peek()){
            stackMin.push(value);
        } else {
            stackMin.push(stackMin.peek());
        }
        size++;
    }

    public void pop(){
        if(size == 0){
            throw new RuntimeException("栈为空！");
        }
        stackMin.pop();
        stackValue.pop();
        size--;
    }

    public int top(){
        if(size == 0){
            throw new RuntimeException("栈为空！");
        }
        return stackValue.peek();
    }

    public int getMin(){
        if(size == 0){
            throw new RuntimeException("栈为空！");
        }
        return stackMin.peek();
    }

    @Override
    public String toString() {
        return "MinStack{" +
                "stackValue=" + stackValue +
                ", stackMin=" + stackMin +
                '}';
    }
}
