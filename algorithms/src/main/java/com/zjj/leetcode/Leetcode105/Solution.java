package com.zjj.leetcode.Leetcode105;

import com.zjj.treeNode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(cur);
        for (int i = 1; i < preorder.length; i++) {
            int pre = map.get(preorder[i - 1]);
            int next = map.get(preorder[i]);
            if (next < pre) {
                cur.left = new TreeNode(preorder[i]);
                cur = cur.left;
                deque.addLast(cur);
            } else {
                while (!deque.isEmpty() && map.get(deque.peekLast().val) < next) {
                    cur = deque.pollLast();
                }
                cur.right = new TreeNode(preorder[i]);
                cur = cur.right;
                deque.addLast(cur);
            }
        }
        return root;
    }
}
