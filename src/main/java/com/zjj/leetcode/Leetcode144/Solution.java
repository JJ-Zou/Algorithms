package com.zjj.leetcode.Leetcode144;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode cur = deque.pop();
            res.add(cur.val);
            if (cur.right != null) {
                deque.push(cur.right);
            }
            if (cur.left != null) {
                deque.push(cur.left);
            }
        }
        return res;
    }
}