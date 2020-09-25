package com.zjj.Leetcode.Leetcode199;

import com.zjj.treeNode.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while(!deque.isEmpty()) {
            int len = deque.size();
            for(int i = 0; i < len; i++) {
                TreeNode cur = deque.pollFirst();
                if(cur.left != null) {
                    deque.addLast(cur.left);
                }
                if(cur.right != null) {
                    deque.addLast(cur.right);
                }
                if(i == len - 1) {
                    list.add(cur.val);
                }
            }
        }
        return list;
    }
}
