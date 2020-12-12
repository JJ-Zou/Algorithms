package com.zjj.Leetcode.Leetcode104;


import com.zjj.treeNode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS层次遍历
 * 用size记录当前层的节点数，
 * 用leaf记录当前层的结点的子节点数（即下层的结点数）
 * 当size=0即此层遍历结束
 */
public class DepthOfTreeDFS {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 1;
        int leaf = 0;
        int depth = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            size--;
            if (t.left != null) {
                queue.add(t.left);
                leaf++;
            }
            if (t.right != null) {
                queue.add(t.right);
                leaf++;
            }
            if (size == 0) {
                depth++;
                size = leaf;
                leaf = 0;
            }
        }
        return depth;
    }
}
