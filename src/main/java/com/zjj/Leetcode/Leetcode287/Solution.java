package com.zjj.Leetcode.Leetcode287;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    //链表找环入口
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
