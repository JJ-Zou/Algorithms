package com.zjj.leetcode.Leetcode90;

import java.util.*;

public class Solution {

    private List<List<Integer>> res;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        res = new ArrayList<>();
        backtrack(new ArrayDeque<>(), nums, 0);
        return res;
    }

    private void backtrack(Deque<Integer> deque, int[] nums, int index) {
        res.add(new ArrayList<>(deque));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            deque.addLast(nums[i]);
            backtrack(deque, nums, i + 1);
            deque.pollLast();
        }
    }
}
