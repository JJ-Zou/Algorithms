package com.ZJJ.Leetcode.Leetcode100;

import com.ZJJ.TreeNode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 非递归，借助队列
 * 广度优先遍历(层次遍历)
 * 迭代过程中，依次将左节点右节点入队(都可)
 */
public class SameTreeBFS {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if(p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.add(p);
        queueQ.add(q);
        while(!queueP.isEmpty() && !queueQ.isEmpty()) {
            if(queueP.peek().val != queueQ.peek().val){
                return false;
            }
            TreeNode peekP = queueP.poll();
            TreeNode peekQ = queueQ.poll();
            if(peekP.left != null && peekQ.left != null){
                queueP.add(peekP.left);
                queueQ.add(peekQ.left);
            }else if(peekP.left != null || peekQ.left != null){
                return false;
            }
            if(peekP.right != null && peekQ.right != null){
                queueP.add(peekP.right);
                queueQ.add(peekQ.right);
            }else if(peekP.right != null || peekQ.right != null){
                return false;
            }
        }
        return true;
    }
}
