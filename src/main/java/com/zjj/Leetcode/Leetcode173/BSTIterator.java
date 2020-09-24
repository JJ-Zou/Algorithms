package com.zjj.Leetcode.Leetcode173;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {
    private final Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        travelMostLeft(root);
    }

    private void travelMostLeft(TreeNode p) {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode cur = stack.pop();
        if (cur.right != null) {
            travelMostLeft(cur.right);
        }
        return cur.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
