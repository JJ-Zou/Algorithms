package com.ZJJ.Leetcode.Leetcode155;

import java.util.Stack;

/**
 * leetcode #155.最小栈
 * 入栈时，当前栈中最小元素比当前值小时时，最小值栈不变
 * 出栈时，当前栈栈顶元素与最小值栈栈顶元素相等时，两栈均出，否则，仅当前栈出栈
 */
public class MinStack1 {
    private Stack<Integer> stackValue;
    private Stack<Integer> stackMin;
    private int size;

    public MinStack1(){
        stackValue = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int value){
        stackValue.push(value);
        if(size == 0 || value <= stackMin.peek()){  //需要加上=
            stackMin.push(value);
        }
        size++;
    }

    public void pop(){
        if(size == 0){
            throw new RuntimeException("栈为空！");
        }
        if(stackValue.peek().equals(stackMin.peek())){  //由于Integer为包装类型，必须用equals比较
            stackMin.pop();
        }
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
        return "MinStack1{" +
                "stackValue=" + stackValue +
                ", stackMin=" + stackMin +
                '}';
    }
}
