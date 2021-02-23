package com.zjj.test.leetcode.LinekedHashMap;

import com.zjj.leetcode.listNode.ListNode;

public class TestLinkedHashMap {

    public static void main(String[] args) {
        ListNode t1 = new ListNode(0);
        t1.next = new ListNode(1);
        ListNode temp = t1.next;
        t1.next.next = new ListNode(2);
        ListNode t2 = new ListNode(3);
        t2.next = t1.next.next;
        t1.next = t2;
        System.out.println(t1);
        System.out.println(temp);
    }


}
