package com.zjj.leetcode.Leetcode987;

import com.zjj.treeNode.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<TreeNode,int[]> map = new HashMap<>();
        map.put(root, new int[]{0, 0, root.val});
        dfs(root, map);
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<int[]>> groups = map.values().stream()
                .sorted((arr1, arr2) -> {
                    if (arr1[0] == arr2[0]) {
                        if (arr1[1] == arr2[1]) {
                            return arr1[2] - arr2[2];
                        }
                        return arr1[1] - arr2[1];
                    }
                    return arr1[0] - arr2[0];
                })
                .collect(Collectors.groupingBy(arr -> arr[1], TreeMap::new, Collectors.toList()));
        return res;
    }

    private void dfs(TreeNode r, Map<TreeNode,int[]> map) {
        if(r == null) {
            return;
        }
        int[] site = map.get(r);
        if(r.left != null) {
            map.put(r.left, new int[] {site[0] + 1, site[1] - 1, r.left.val});
            dfs(r.left, map);
        }
        if(r.right != null) {
            map.put(r.right, new int[] {site[0] + 1, site[1] + 1, r.right.val});
            dfs(r.right, map);
        }
    }
}

