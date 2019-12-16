package com.ZJJ.Leetcode.Leetcode21;

import com.ZJJ.Node.Node;

/**
 * 归并，递归
 */
public class MergeListNode {
    public Node<Integer> mergeTwoLists(Node<Integer> l1, Node<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.getValue() < l2.getValue()) {
            l1.setNext(mergeTwoLists(l1.getNext(), l2));
            return l1;
        } else {
            l2.setNext(mergeTwoLists(l2.getNext(), l1));
            return l2;
        }
    }
}
