package com.zjj.leetcode.Leetcode863;

import com.zjj.treeNode.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private Map<TreeNode, TreeNode> parents;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println(new Solution().distanceK(root, root.left, 2));
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parents = new HashMap<>();
        dfs(root, null);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        int count = 0;
        while (!deque.isEmpty()) {
            if (count == K) {
                return deque.stream().map(t -> t.val).collect(Collectors.toList());
            }
            count++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.pollFirst();
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    deque.addLast(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    deque.addLast(cur.right);
                }
                TreeNode parent = parents.get(cur);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    deque.addLast(parent);
                }
            }
        }
        return new ArrayList<>();
    }

    private void dfs(TreeNode root, TreeNode cur) {
        if (root == null) {
            return;
        }
        parents.put(root, cur);
        dfs(root.left, root);
        dfs(root.right, root);
    }
}