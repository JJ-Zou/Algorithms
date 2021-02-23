package com.zjj.leetcode.Leetcode148;

import com.zjj.leetcode.listNode.ListNode;

/**
 * 链表归并排序，迭代
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode cur = head;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        for (int step = 1; step < len; step *= 2) {
            ListNode pre = newHead;
            for (int start = 0; start + step < len; start += 2 * step) {
                ListNode left = pre.next;
                ListNode right = left;
                for (int i = 0; i < step; i++) {
                    right = right.next;
                }
                int l = step;
                int r = step;
                while (l > 0 && r > 0 && right != null) {
                    if (right.val < left.val) {
                        r--;
                        pre.next = right;
                        pre = right;
                        right = right.next;
                    } else {
                        l--;
                        pre.next = left;
                        pre = left;
                        left = left.next;
                    }
                }
                while (l > 0) {
                    l--;
                    pre.next = left;
                    pre = left;
                    left = left.next;
                }
                while (r > 0 && right != null) {
                    r--;
                    pre.next = right;
                    pre = right;
                    right = right.next;
                }
                pre.next = right;
            }
        }
        return newHead.next;
    }
}
