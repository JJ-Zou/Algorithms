package com.zjj.leetcode.Leetcode222;

import com.zjj.treeNode.TreeNode;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (depth(root) == 1) {
            return 1;
        }
        int l = depth(root.left);
        int r = depth(root.right);
        if (l == r) {
            return (1 << l) + countNodes(root.right);
        } else {
            return (1 << r) + countNodes(root.left);
        }
    }

    private int depth(TreeNode p) {
        if (p == null) {
            return 0;
        }
        int d = 0;
        while (p != null) {
            d++;
            p = p.left;
        }
        return d;
    }
}
