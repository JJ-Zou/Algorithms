package com.zjj.nowcoder.ex1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        rotate(new String[]{"1", "2", "3", "#", "#", "4", "5"}, 1);
    }

    static class TreeNode {
        String val;
        TreeNode next;
        TreeNode left;
        TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            TreeNode cur = this;
            while (cur.next != null) {
                sb.append(cur.val).append("->");
                cur = cur.next;
            }
            sb.append(cur.val);
            return sb.toString();
        }
    }

    private static void rotate(String[] str, int k) {
        List<TreeNode> list = new ArrayList<>();
        int len = str.length;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        while (!deque.isEmpty()) {
            int size = deque.size();
            TreeNode head = null;
            TreeNode cur = null;
            for (int i = 0; i < size; i++) {
                int curIndex = deque.pollFirst();
                if (i == 0) {
                    head = new TreeNode(str[curIndex]);
                    cur = head;
                }
                if (i > 0 && i < size - 1) {
                    cur.next = new TreeNode(str[curIndex]);
                    cur = cur.next;
                }
                if (i == size - 1) {
                    if (i > 0) {
                        cur.next = new TreeNode(str[curIndex]);
                        cur = cur.next;
                    }
                    list.add(head);
                }
                if (curIndex * 2 + 1 < len) {
                    deque.addLast(curIndex * 2 + 1);
                }
                if (curIndex * 2 + 2 < len) {
                    deque.addLast(curIndex * 2 + 2);
                }
            }
        }
        System.out.println(list);
    }
}
