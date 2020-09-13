package com.zjj.Leetcode.Leetcode114;

import com.zjj.treeNode.TreeNode;

public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode t = cur.left;
                pre = t;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.left = null;
                cur.right = t;
            }
            cur = cur.right;
        }
    }
}
