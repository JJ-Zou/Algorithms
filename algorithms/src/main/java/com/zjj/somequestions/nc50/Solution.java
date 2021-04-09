package com.zjj.somequestions.nc50;

import com.zjj.leetcode.listNode.ListNode;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new Solution().reverseKGroup(head, 2));
    }

    /**
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (len == 0 || k == 1 || k > len) {
            return head;
        }
        ListNode newHead = null;
        cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = temp;
        }
        cur = newHead;
        newHead = null;
        for (int i = 0; i < len % k; i++) {
            ListNode temp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = temp;
        }
        ListNode res = null;
        while (cur != null) {
            res = cur;
            for (int i = 1; i < k; i++) {
                cur = cur.next;
            }
            ListNode temp = cur.next;
            cur.next = newHead;
            cur = temp;
            newHead = res;
        }
        return res;
    }
}