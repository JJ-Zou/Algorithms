package com.zjj.Leetcode.Leetcode117;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node p = this;
        while (p != null) {
            Node c = p;
            while (c != null) {
                sb.append(c.val).append("->");
                c = c.next;
            }
            while (p != null && p.left == null && p.right == null) {
                p = p.next;
            }
            sb.append("null");
            if (p == null) {
                return sb.toString();
            }
            sb.append("->");
            p = (p.left != null ? p.left : p.right);
        }
        return sb.toString();
    }
}

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, null, new Node(7), null),
                null);
        System.out.println(solution.connect(root));
    }

    public Node connect(Node root) {
        Node cur = root;
        while (cur != null) {
            Node father = cur;
            while (father != null && father.left == null && father.right == null) {
                father = father.next;
            }
            if (father == null) {
                return root;
            }
            cur = (father.left != null ? father.left : father.right);
            while (father != null) {
                if (father.left != null && father.right != null) {
                    father.left.next = father.right;
                }
                Node p = father.next;
                while (p != null && p.left == null && p.right == null) {
                    p = p.next;
                }
                if (p == null) {
                    break;
                }
                if (father.right == null) {
                    father.left.next = (p.left != null ? p.left : p.right);
                } else {
                    father.right.next = (p.left != null ? p.left : p.right);
                }
                father = p;
            }
        }
        return root;
    }
}
