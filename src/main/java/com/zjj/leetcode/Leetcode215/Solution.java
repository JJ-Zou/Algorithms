package com.zjj.leetcode.Leetcode215;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest1(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }


    public int findKthLargest1(int[] nums, int k) {
        return findK(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findK(int[] nums, int k, int start, int end) {
        int index = partition(nums, start, end);
        if (index == k) {
            return nums[index];
        } else if (index > k) {
            return findK(nums, k, start, index - 1);
        }
        return findK(nums, k, index + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        swap(nums, start, new Random().nextInt(end - start + 1) + start);
        int pivot = nums[start];
        int left = start - 1;
        int right = end + 1;
        int cur = start + 1;
        while (cur < right) {
            if (nums[cur] < pivot) {
                swap(nums, cur++, ++left);
            } else if (nums[cur] > pivot) {
                swap(nums, cur, --right);
            } else {
                cur++;
            }
        }
        return left + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
