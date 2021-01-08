package com.zjj.leetcode.Leetcode169;

/**
 * 摩尔投票
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int cur = 0;
        int vote = 0;
        int more = nums[0];
        while (cur < nums.length) {
            if (vote == 0) {
                more = nums[cur];
            }
            vote += (nums[cur++] == more) ? 1 : -1;
        }
        return more;
    }
}
