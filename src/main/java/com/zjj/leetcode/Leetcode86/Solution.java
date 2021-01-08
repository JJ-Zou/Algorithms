package com.zjj.leetcode.Leetcode86;

import com.zjj.leetcode.listNode.ListNode;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode left = head;
        while (left.next != null && left.next.val < x) {
            left = left.next;
        }
        ListNode newHead = new ListNode(-1);
        if (head.val >= x) {
            left = newHead;
            left.next = head;
        }
        ListNode pre = left;
        ListNode cur = left.next;
        ListNode target = null;
        while (cur != null) {
            if (cur.val >= x || target == null) {
                if (cur.val == x || cur.next == null || (cur.val > x && cur.next.val < x)) {
                    target = cur;
                }
                pre = pre.next;
                cur = cur.next;
            } else {
                ListNode t = cur.next;
                cur.next = left.next;
                left.next = cur;
                left = cur;
                pre.next = t;
                cur = t;
            }
        }
        return head.val >= x ? newHead.next : head;
    }
}
