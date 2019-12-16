package com.ZJJ.Collection.Queue;

import org.junit.Test;

public class TestQueue {
    @Test
    public void TestQueueSingle() throws Exception {
        QueueSingleListNode<Integer> singleListNode = new QueueSingleListNode<>();
        singleListNode.enqueue(1);
        singleListNode.enqueue(2);
        singleListNode.enqueue(3);
        for (Integer integer : singleListNode) {
            System.out.println(integer);
        }
        System.out.println(singleListNode);
        System.out.println(singleListNode.getHeadValue());
        System.out.println(singleListNode.getTail().getValue());
        System.out.println(singleListNode.dequeue());
        System.out.println(singleListNode);
        System.out.println(singleListNode.getHead());
        System.out.println(singleListNode.getTail());
        System.out.println(singleListNode.dequeue());
        System.out.println(singleListNode);
        System.out.println(singleListNode.getHeadValue());
        System.out.println(singleListNode.getTailValue());
    }
}
