package com.zz.Leetcode.Leetcode100;


import com.zz.treeNode.TreeNode;

import java.util.Stack;

/**
 * 非递归，借助栈
 * 深度优先（前序遍历）
 * 在迭代过程中，先将右节点入栈，再将左节点入栈
 */
public class SameTreeDFS {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        stackP.push(p);
        stackQ.push(q);
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            if (stackP.peek().val != stackQ.peek().val) {
                return false;
            }
            TreeNode peekP = stackP.pop();
            TreeNode peekQ = stackQ.pop();
            if (peekP.right != null && peekQ.right != null) {
                stackP.push(peekP.right);
                stackQ.push(peekQ.right);
            } else if (peekP.right != null || peekQ.right != null) {
                return false;
            }
            /**
             * 这段可以不要，那么所有的判断将在出栈时进行
             if(!stackP.isEmpty() && !stackQ.isEmpty() &&
             (stackP.peek().val != stackQ.peek().val)){
             return false;
             }
             **/
            if (peekP.left != null && peekQ.left != null) {
                stackP.push(peekP.left);
                stackQ.push(peekQ.left);
            } else if (peekP.left != null || peekQ.left != null) {
                return false;
            }
        }
        /**
         * 跳出循环时，所有元素已经比较晚且相等
         */
        return true;
    }
}
