package com.zjj.traversal;


import java.util.ArrayDeque;
import java.util.Deque;


public class Traversal {


    public void postOrderTraversal(com.ZJJ.TreeNode.TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<com.ZJJ.TreeNode.TreeNode> stack = new ArrayDeque<>();
        com.ZJJ.TreeNode.TreeNode lastVisited = root;
        com.ZJJ.TreeNode.TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == lastVisited) {
                System.out.print(cur.val + " ");
                stack.pop();
                lastVisited = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public void postOrderTraversalVersion2(com.ZJJ.TreeNode.TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<com.ZJJ.TreeNode.TreeNode> stack = new ArrayDeque<>();
        com.ZJJ.TreeNode.TreeNode lastVisited = null;
        com.ZJJ.TreeNode.TreeNode cur = root;
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) ||
                    (lastVisited != null && (lastVisited == cur.left || lastVisited == cur.right))) {
                System.out.print(cur.val + " ");
                stack.pop();
                lastVisited = cur;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        System.out.println();
    }
}
