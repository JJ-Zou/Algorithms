package com.zjj.leetcode.Leetcode239;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < nums.length) {
            return new int[0];
        }
        int[] max = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int left = 0;
        int right = 0;
        for (; right < k; right++) {
            while (!deque.isEmpty() && nums[right] > deque.peekLast()) {
                deque.pollLast();
            }
            deque.addLast(nums[right]);
        }
        max[left] = deque.peekFirst();
        while (right < nums.length) {
            if (nums[left++] == deque.peekFirst()) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[right] > deque.peekLast()) {
                deque.pollLast();
            }
            deque.addLast(nums[right++]);
            max[left] = deque.peekFirst();
        }
        return max;
    }
}
