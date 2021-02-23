package com.zjj.leetcode.offer68Ⅱ;

import com.zjj.treeNode.TreeNode;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        root.left = p;
        root.right = q;
        p.left = new TreeNode(6);
        p.right = new TreeNode(2);
        p.right.left = new TreeNode(7);
        p.right.right = new TreeNode(4);
        q.left = new TreeNode(0);
        q.right = new TreeNode(8);
        System.out.println(solution.lowestCommonAncestor(root, p, q));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftAns = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAns = lowestCommonAncestor(root.right, p, q);
        if (leftAns == null) {
            return rightAns;
        }
        if (rightAns == null) {
            return leftAns;
        }
        return root;
    }
}
