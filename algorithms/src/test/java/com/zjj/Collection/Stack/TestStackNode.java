package com.zjj.Collection.Stack;

import com.zjj.Collection.Stack.StackListNode.StackListNode;
import org.junit.Test;

public class TestStackNode {
    @Test
    public void TestStack() throws Exception {
        StackListNode<String> stringStackListNode = new StackListNode<>();
//        System.out.println(stringStackListNode.size());
        stringStackListNode.push("1");
        stringStackListNode.push("2");
        stringStackListNode.push("3");
        stringStackListNode.push("4");
        stringStackListNode.push("5");
        for (String string : stringStackListNode) {
            System.out.println(string);
        }
//        System.out.println(stringStackListNode);
//        for (int i=0;i<5;i++){
//            System.out.println(stringStackListNode.peek());
//            System.out.println(stringStackListNode.pop());
//        }
//        System.out.println(stringStackListNode);
    }
}
