package com.zjj.Leetcode.Leetcode143;

import com.zjj.Leetcode.listNode.ListNode;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(head);
        solution.reorderList(head);
        System.out.println(head);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        ListNode cur = newHead.next;
        newHead.next = null;
        slow.next = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = t;
        }
        slow = head;
        cur = newHead;
        while (cur != null) {
            ListNode t1 = slow.next;
            ListNode t2 = cur.next;
            slow.next = cur;
            cur.next = t1;
            slow = t1;
            cur = t2;
        }
    }
}