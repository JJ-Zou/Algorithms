package com.zjj.Leetcode.Leetcode230;

import com.zjj.treeNode.TreeNode;

public class Solution {
    private int count = 0;
    private int res = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    private void dfs(TreeNode p, int k) {
        if (p == null || count == k) {
            return;
        }
        dfs(p.left, k);
        count++;
        if (count == k) {
            res = p.val;
            return;
        }
        dfs(p.right, k);
    }
}
