package com.zjj.Leetcode.Leetcode94;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        System.out.println(solution.inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode cur = deque.peek();
            if (cur.left != null) {
                deque.push(cur.left);
            } else {
                res.add(deque.pop().val);
                if (!deque.isEmpty()) {
                    cur = deque.pop();
                    res.add(cur.val);
                }
                if (cur.right != null) {
                    deque.push(cur.right);
                }
            }
        }
        return res;
    }
}
