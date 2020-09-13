package com.zjj.Leetcode.Leetcode92;

import com.zjj.Leetcode.listNode.ListNode;

public class ReverseListNode {
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        int count = 1;
        if (m == 1) {
            ListNode newHead = new ListNode(-1);
            ListNode cur = head;
            while (cur != null) {
                ListNode t = cur.next;
                cur.next = newHead.next;
                newHead.next = cur;
                cur = t;
                if (count == n) {
                    head.next = cur;
                    return newHead.next;
                }
                count++;
            }
        }
        ListNode cur = head;
        while (count != m - 1) {
            cur = cur.next;
            count++;
        }
        cur.next = reverseBetween2(cur.next, 1, n - count);
        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        ListNode beforeM = new ListNode(-1);
        beforeM.next = head;
        ListNode atM = null;
        ListNode atN = null;
        ListNode cur = null;
        for (int i = 1; i < m; i++) {
            beforeM = beforeM.next;
        }
        atN = beforeM.next;
        atM = atN;
        if (atM == null) {
            return head;
        }
        cur = atM.next;
        for (int i = m; i <= n - 1; i++) {
            beforeM.next = cur;
            atN.next = cur.next;
            cur.next = atM;
            cur = atN.next;
            atM = beforeM.next;
        }
        return m == 1 ? beforeM.next : head;
    }
}
