package com.zjj.Leetcode.Leetcode328;

import com.zjj.Leetcode.listNode.ListNode;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode right = head.next;
        ListNode p1 = head;
        ListNode p2 = right;
        while (p2 != null && p2.next != null) {
            ListNode temp = p2.next;
            p1.next = temp;
            p2.next = temp.next;
            p1 = temp;
            p2 = temp.next;
        }
        p1.next = right;
        return head;
    }
}
