package com.zjj.leetcode.Leetcode92;

import com.zjj.leetcode.listNode.ListNode;

public class ReverseListNode {
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
