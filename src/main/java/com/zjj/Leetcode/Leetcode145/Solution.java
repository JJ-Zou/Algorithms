package com.zjj.Leetcode.Leetcode145;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode last = root;
        TreeNode cur = root;
        while (!deque.isEmpty() || cur != null) {
            while (cur != null) {
                deque.push(cur);
                cur = cur.left;
            }
            cur = deque.peek();
            if (cur.right == null || cur.right == last) {
                res.add(cur.val);
                deque.pop();
                last = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }
}