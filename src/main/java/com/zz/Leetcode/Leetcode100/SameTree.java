package com.zz.Leetcode.Leetcode100;


import com.zz.treeNode.TreeNode;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 递归，判断两个数的根节点与左子树右子树是否全相同
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }
}
