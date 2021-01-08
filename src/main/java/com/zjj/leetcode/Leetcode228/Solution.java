package com.zjj.leetcode.Leetcode228;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int len = nums.length;
        int cur = 0;
        while (cur < len) {
            StringBuilder sb = new StringBuilder();
            sb.append(nums[cur]);
            if (cur + 1 < len && nums[cur] + 1 == nums[cur + 1]) {
                while (cur + 1 < len && nums[cur] + 1 == nums[cur + 1]) {
                    cur++;
                }
                sb.append("->").append(nums[cur]);
            }
            list.add(sb.toString());
            cur++;
        }
        return list;
    }
}
