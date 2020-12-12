package com.zjj.Leetcode.Leetcode106;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int len = postorder.length;
        TreeNode root = new TreeNode(postorder[len - 1]);
        TreeNode cur = root;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        for (int i = len - 2; i >= 0; i--) {
            int pre = map.get(postorder[i + 1]);
            int next = map.get(postorder[i]);
            if (next > pre) {
                cur.right = new TreeNode(postorder[i]);
                cur = cur.right;
                deque.addLast(cur);
            } else {
                while (!deque.isEmpty() && map.get(deque.peekLast().val) > next) {
                    cur = deque.pollLast();
                }
                cur.left = new TreeNode(postorder[i]);
                cur = cur.left;
                deque.addLast(cur);
            }
        }
        return root;
    }
}
