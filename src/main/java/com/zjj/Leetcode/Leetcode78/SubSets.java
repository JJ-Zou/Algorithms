package com.zjj.Leetcode.Leetcode78;

import java.util.*;

public class SubSets {
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> every = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >>> j) & 1) == 1) {
                    every.add(nums[j]);
                }
            }
            list.add(every);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new SubSets().subsets(new int[]{1, 2, 3}));
    }

    private List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        backtrack(nums, new ArrayDeque<>(), 0);
        return res;
    }

    private void backtrack(int[] nums, Deque<Integer> deque, int index) {
        res.add(new ArrayList<>(deque));
        for (int i = index; i < nums.length; i++) {
            deque.addLast(nums[i]);
            System.out.println("before => " + deque);
            backtrack(nums, deque, i + 1);
            System.out.println("after => " + deque);
            deque.pollLast();
        }
    }
}
