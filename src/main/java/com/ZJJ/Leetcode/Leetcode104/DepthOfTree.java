package com.ZJJ.Leetcode.Leetcode104;

import com.ZJJ.TreeNode.TreeNode;

/**
 * 递归，最大深度 = 子树的最大深度 +1
 */
public class DepthOfTree {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
