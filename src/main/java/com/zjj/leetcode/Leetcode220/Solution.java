package com.zjj.Leetcode.Leetcode220;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                map.remove(getBucket(nums[i - k - 1], (long) t + 1));
            }
            long bucket = getBucket(nums[i], (long) t + 1);
            if (map.containsKey(bucket)) {
                return true;
            }
            if (map.containsKey(bucket + 1) && map.get(bucket + 1) - nums[i] <= t) {
                return true;
            }
            if (map.containsKey(bucket - 1) && nums[i] - map.get(bucket - 1) <= t) {
                return true;
            }
            map.put(bucket, (long) nums[i]);
        }
        return false;
    }

    private long getBucket(long num, long len) {
        if (num >= 0) {
            return num / len;
        }
        return (num + 1) / len - 1;
    }
}
