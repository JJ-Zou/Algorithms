package com.ZJJ.Collection.Stack;

import com.ZJJ.Collection.Stack.StackFixed.StackFixed;
import com.ZJJ.Collection.Stack.StackVariable.StackVariable;
import org.junit.Test;


public class StackTest {
    @Test
    public void StackString() throws Exception {
        StackFixed<String> stringStack = new StackFixed<>();
        System.out.println(stringStack.capacity());
        for (int i = 0; i < 16; i++) {
            stringStack.push("String " + i);
        }
        for (String string : stringStack) {
            System.out.println(string);
        }
        System.out.println(stringStack.pop());
        System.out.println(stringStack);
        System.out.println(stringStack.peek());
    }

    @Test
    public void StackInteger() throws Exception {
        StackFixed<Integer[]> stringStack = new StackFixed<>(42);
        System.out.println(stringStack.capacity());
        for (int i = 0; i < 16; i++) {
            stringStack.push(new Integer[]{i});
        }
        System.out.println(stringStack.peek());
    }

    @Test
    public void StackVar() throws Exception {
        StackVariable<String> stringStack = new StackVariable<>();
        System.out.println(stringStack.capacity());
        for (int i = 0; i < 17; i++) {
            stringStack.push("V:" + i);
        }
        System.out.println(stringStack);
        System.out.println(stringStack.capacity());
        for (int i = 0; i < 17; i++) {
            stringStack.pop();
            System.out.println(stringStack);
            System.out.println(stringStack.capacity());
        }
        System.out.println(stringStack.peek());
    }
}
