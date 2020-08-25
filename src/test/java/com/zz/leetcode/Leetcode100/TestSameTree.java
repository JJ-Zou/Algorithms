package com.zz.leetcode.Leetcode100;

import com.zz.treeNode.TreeNode;
import org.junit.Test;

public class TestSameTree {
    @Test
    public void testSameTree() throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root.left.right = new TreeNode(3);

        SameTree sameTree = new SameTree();
        System.out.println(sameTree.isSameTree(root, root1));
    }
}
