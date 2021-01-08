package com.zjj.leetcode.Leetcode113;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    private List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        dfs(root, new ArrayDeque<>(), sum);
        return res;
    }

    private void dfs(TreeNode p, Deque<Integer> deque, int sum) {
        if (p == null) {
            return;
        }
        deque.addLast(p.val);
        if (p.left == null && p.right == null && sum == p.val) {
            res.add(new ArrayList<>(deque));
        }
        if (p.left != null) {
            dfs(p.left, deque, sum - p.val);
        }
        if (p.right != null) {
            dfs(p.right, deque, sum - p.val);
        }
        deque.pollLast();
    }
}
