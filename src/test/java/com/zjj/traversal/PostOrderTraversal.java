package com.zjj.traversal;

import com.zjj.treeNode.TreeNode;
import org.junit.Test;

public class PostOrderTraversal {
    @Test
    public void travel() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        new Traversal().postOrderTraversal(root);
        new Traversal().postOrderTraversalVersion2(root);
    }
}
