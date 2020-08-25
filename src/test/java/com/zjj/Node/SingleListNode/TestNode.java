package com.zjj.Node.SingleListNode;

import com.zjj.Node.Node;
import org.junit.Test;


public class TestNode {
    @Test
    public void TestNode() throws Exception {
        Node<Integer> node = new Node(1);
        Node<Integer> node1 = new Node<>(2);
        node.setNext(node1);
        System.out.println(node);
    }

    @Test
    public void NodeTest() throws Exception {
        SingleListNode<Integer> listNode = new SingleListNode<>();
        listNode.addFirst(4);
        listNode.addFirst(5);
        listNode.addFirst(0);
        listNode.addFirst(0);
        System.out.println(listNode.toString());
        listNode.updateNode(1, 1);
        System.out.println(listNode);
        System.out.println(listNode.size());
    }
}
