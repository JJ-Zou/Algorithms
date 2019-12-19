package com.ZJJ.Leetcode.Leetcode110;

import com.ZJJ.TreeNode.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
 * 递归，其子树也是平衡二叉树，且左右两个子树的高度差的绝对值不超过1
 */
public class ISBST {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(size(root.left) - size(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    private int size(TreeNode t) {
        if (t == null) {
            return 0;
        }
        return 1 + Math.max(size(t.left), size(t.right));
    }
}
