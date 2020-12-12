package com.zjj.Leetcode.Leetcode324;

import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
    }

    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int mid = (len - 1) / 2;
        getMiddle(nums, mid, 0, len - 1);
        int[] temp = nums.clone();
        int read = mid;
        for (int i = 0; i < len; i += 2) {
            nums[i] = temp[read];
            read--;
        }
        read = len - 1;
        for (int i = 1; i < len; i += 2) {
            nums[i] = temp[read];
            read--;
        }
    }

    private void getMiddle(int[] nums, int mid, int left, int right) {
        int index = partition(nums, left, right);
        if (index == mid) {
            return;
        } else if (index > mid) {
            getMiddle(nums, mid, left, index - 1);
            return;
        }
        getMiddle(nums, mid, index + 1, right);
    }

    private int partition(int[] nums, int start, int end) {
        swap(nums, start, new Random().nextInt(end - start + 1) + start);
        int left = start - 1;
        int right = end + 1;
        int pivot = nums[start];
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
