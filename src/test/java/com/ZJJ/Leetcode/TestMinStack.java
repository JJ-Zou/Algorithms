package com.ZJJ.Leetcode;

import com.ZJJ.Leetcode.Leetcode155.MinStack;
import com.ZJJ.Leetcode.Leetcode155.MinStack1;
import org.junit.Test;

public class TestMinStack {
    @Test
    public void testMinStack() throws Exception {
        MinStack minStack = new MinStack();
        minStack.push(1);
//        System.out.println(minStack.getMin());
        minStack.push(-1);
//        System.out.println(minStack.getMin());
        minStack.push(2);
//        System.out.println(minStack.getMin());
        minStack.push(-9);
//        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack);
    }

    @Test
    public void testMinStack1() throws Exception {
        MinStack1 minStack = new MinStack1();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        System.out.println(minStack);
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}
