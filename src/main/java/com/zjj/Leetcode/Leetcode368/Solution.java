package com.zjj.Leetcode.Leetcode368;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        List<Integer>[] dp = (ArrayList<Integer>[]) new ArrayList[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new ArrayList<>();
        }
        Arrays.sort(nums);
        dp[0].add(nums[0]);
        for (int i = 1; i < len; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j].size() > temp.size()) {
                    temp = new ArrayList<>(dp[j]);
                }
            }
            dp[i].addAll(temp);
            dp[i].add(nums[i]);
        }
        for (List<Integer> list : dp) {
            if (list.size() > res.size()) {
                res = list;
            }
        }
        return res;
    }
}
