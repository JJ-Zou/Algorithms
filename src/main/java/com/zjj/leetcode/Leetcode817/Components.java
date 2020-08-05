package com.zjj.leetcode.Leetcode817;

import com.zjj.leetcode.listNode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 当前节点在G中，而下个节点为空（即当前节点为最后一个节点）或下个节点不在G中时，组件个数+1
 */
public class Components {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int num : G) {
            set.add(num);
        }
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val) && (cur.next == null || !set.contains(cur.next.val))) {
                count++;
            }
            cur = cur.next;
        }
        return count;
    }
}
