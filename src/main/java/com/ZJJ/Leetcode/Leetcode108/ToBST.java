package com.ZJJ.Leetcode.Leetcode108;

import com.ZJJ.TreeNode.TreeNode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 递归
 * 二分法寻找父节点
 */
public class ToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums, start, mid - 1);
        root.right = toBST(nums, mid + 1, end);
        return root;
    }
}
