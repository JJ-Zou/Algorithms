package com.zz.Leetcode.Leetcode101;


import com.zz.treeNode.TreeNode;

/**
 * 递归
 * 给定一个二叉树，检查它是否是镜像对称的，即此数是否与自己呈镜像对称
 * 两树呈镜像对称，则它们各自的左子树与对方的右子树呈镜像对称
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val &&
                isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
