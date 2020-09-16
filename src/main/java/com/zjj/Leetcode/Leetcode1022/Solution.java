package com.zjj.Leetcode.Leetcode1022;

import com.zjj.treeNode.TreeNode;

public class Solution {
    long sum = 0l;

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);
        System.out.println(solution.sumRootToLeaf(root));
    }

    public int sumRootToLeaf(TreeNode root) {
        back(root, new StringBuilder());
        return (int) (sum % 1000000007);
    }

    private void back(TreeNode p, StringBuilder sb) {
        if (p == null) {
            sum += Integer.parseInt(sb.toString(), 2);
            return;
        }
        sb.append(p.val);
        back(p.left, sb);
        back(p.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
