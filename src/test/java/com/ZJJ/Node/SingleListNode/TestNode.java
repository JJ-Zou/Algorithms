package com.ZJJ.Node.SingleListNode;

import com.ZJJ.Node.Node;
import org.junit.Test;


public class TestNode {
    @Test
    public void TestNode() throws Exception {
        Node<Integer> node = new Node<>();
        System.out.println(node);
    }

    @Test
    public void NodeTest() throws Exception {
        SingleListNode<String> listNode = new SingleListNode<>(new Node("1"));
        listNode.updateNode("2",0);
        listNode.addNode("0",0);
        listNode.addNode("1",1);
        listNode.addNode("9",99);
        System.out.println(listNode);
        listNode.deleteNode(0);
        System.out.println(listNode.findNode(0));
        listNode.addNode("9",99);
        listNode.addNode("7",3);
        System.out.println(listNode);
    }
}
