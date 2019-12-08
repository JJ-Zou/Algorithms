package com.ZJJ.Node.SingleListNode;

import org.junit.Test;

public class TestNode {
    @Test
    public void NodeTest() throws Exception {
        SingleListNode<String> first = new SingleListNode<>();
        first.value="1";
        SingleListNode<String> second = new SingleListNode<>();
        second.value="2";
        SingleListNode<String> third = new SingleListNode<>();
        third.value="3";
        SingleListNode<String> forth = new SingleListNode<>();
        forth.value="4";
        SingleListNode<String> fifth = new SingleListNode<>();
        fifth.value="5";
        first.next=second;
        second.next=third;
        third.next=forth;
        forth.next=fifth;
        System.out.println(first.value+" "+first.next.value);
        System.out.println(first);
    }
}
