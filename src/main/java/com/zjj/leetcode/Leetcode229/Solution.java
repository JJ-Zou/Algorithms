package com.zjj.leetcode.Leetcode229;

import java.util.ArrayList;
import java.util.List;

/**
 * 摩尔投票
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int candidate1 = nums[0];
        int candidate2 = nums[0];
        int vote1 = 0;
        int vote2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                vote1++;
                continue;
            }
            if (num == candidate2) {
                vote2++;
                continue;
            }
            if (vote1 == 0) {
                candidate1 = num;
                vote1++;
                continue;
            }
            if (vote2 == 0) {
                candidate2 = num;
                vote2++;
                continue;
            }
            vote1--;
            vote2--;
        }
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }
}
