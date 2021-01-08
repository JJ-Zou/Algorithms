package com.zjj.leetcode.Leetcode398;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
    private int[] nums;
    private int len;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
        len = nums.length;
    }

    public int pick(int target) {
        int index = -1;
        int sample = 0;
        for (int i = 0; i < len; i++) {
            if (target == nums[i]) {
                sample++;
                if (random.nextInt(sample) == 0) {
                    index = i;
                }
            }
        }
        return index;
    }
}