package com.zz.traversal;


import com.zz.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;


public class Traversal {


    public void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = root;
        TreeNode cur = root;
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

    public void postOrderTraversalVersion2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = null;
        TreeNode cur = root;
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
