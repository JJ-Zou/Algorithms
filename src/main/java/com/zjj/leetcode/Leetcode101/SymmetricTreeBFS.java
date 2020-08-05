package com.zjj.leetcode.Leetcode101;


import com.zjj.treeNode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTreeBFS {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode tL = queue.poll();
            TreeNode tR = queue.poll();
            if (tL == null && tR == null) {
                continue;
            }
            if (tL == null || tR == null) {
                return false;
            }
            if (tL.val != tR.val) {
                return false;
            }
            queue.add(tL.left);
            queue.add(tR.right);
            queue.add(tL.right);
            queue.add(tR.left);
        }
        return true;
    }
}
