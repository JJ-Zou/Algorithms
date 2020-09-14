package com.zjj.Leetcode.Leetcode116;

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
}

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null),
                null);
        System.out.println(solution.connect(root));
    }

    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    private void dfs(Node p) {
        if (p == null) {
            return;
        }
        if (p.left != null && p.right != null) {
            p.left.next = p.right;
            if (p.next != null) {
                p.right.next = p.next.left;
            }
        }
        dfs(p.left);
        dfs(p.right);
    }
}
