package com.zjj.Leetcode.Leetcode39;

import java.util.*;

public class Solution {
    private List<List<Integer>> res;

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        back(candidates, 0, candidates.length, target, new ArrayDeque<>());
        return res;
    }

    private void back(int[] candidates, int fromIndex, int toIndex,
                      int target, Deque<Integer> deque) {
        if (target == 0) {
            res.add(new ArrayList<>(deque));
            return;
        }
        for (int i = fromIndex; i < toIndex; i++) {
            if (target - candidates[i] < 0) {
                return;
            }
            deque.addLast(candidates[i]);
            System.out.println("递归前：" + deque + ", target = " + (target - candidates[i]));
            back(candidates, i, candidates.length, target - candidates[i], deque);
            System.out.println("递归后：" + deque + ", target = " + target);
            deque.pollLast();
        }
    }
}
