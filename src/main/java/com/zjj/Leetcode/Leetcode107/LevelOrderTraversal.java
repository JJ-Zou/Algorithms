package com.zjj.Leetcode.Leetcode107;


import com.zjj.treeNode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 广度优先遍历（头插）
 */
public class LevelOrderTraversal {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return (List<List<Integer>>) res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int number = queue.size();
            List<Integer> list = new LinkedList<>();
            while (number > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                number--;
            }
            res.addFirst(list);
        }
        return (List<List<Integer>>) res;
    }
}
