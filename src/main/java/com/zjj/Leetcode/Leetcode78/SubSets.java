package com.zjj.Leetcode.Leetcode78;

import java.util.LinkedList;
import java.util.List;

public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
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
}
