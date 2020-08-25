package com.zz.Leetcode.Leetcode1;

import java.util.HashMap;
import java.util.Map;

/**
 * 用哈希表存储 值-序号 对
 * 在遍历到map中存在target与当前值之差时得到结果
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
