package com.zjj.leetcode.Leetcode337;

import com.zjj.treeNode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    private Map<TreeNode, Integer> selected;
    private Map<TreeNode, Integer> unselected;

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        selected = new HashMap<>();
        unselected = new HashMap<>();
        dfs(root);
        return Math.max(selected.get(root), unselected.get(root));
    }

    private void dfs(TreeNode p) {
        if (p == null) {
            return;
        }
        dfs(p.left);
        dfs(p.right);
        selected.put(p, p.val + unselected.getOrDefault(p.left, 0) + unselected.getOrDefault(p.right, 0));
        unselected.put(p,
                Math.max(selected.getOrDefault(p.left, 0), unselected.getOrDefault(p.left, 0)) +
                        Math.max(selected.getOrDefault(p.right, 0), unselected.getOrDefault(p.right, 0)));
    }
}
