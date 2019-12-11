package com.ZJJ.Leetcode.Leetcode21;

import com.ZJJ.Node.Node;

/**
 * 归并，非递归
 */
public class MergeTwoListNode {
    public Node<Integer> mergeTwoLists(Node<Integer> l1, Node<Integer> l2) {
        Node<Integer> mergeList = new Node(-1);
        Node<Integer> cur = mergeList;
        while(l1 != null && l2 != null){
            if(l1.getValue() < l2.getValue()){
                cur.setNext(l1);
                l1 = l1.getNext();
            }else {
                cur.setNext(l2);
                l2 = l2.getNext();
            }
            cur = cur.getNext();
        }
        if(l1 != null) {
            cur.setNext(l1);
        }
        if(l2 != null) {
            cur.setNext(l1);
        }
        return mergeList.getNext();
    }
}
