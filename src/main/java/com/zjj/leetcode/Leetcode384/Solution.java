package com.zjj.leetcode.Leetcode384;

import java.util.Random;

public class Solution {
    private final int[] originArr;

    private int[] array;
    private Random random;

    public Solution(int[] nums) {
        this.originArr = nums.clone();
        this.array = nums;
        random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        array = originArr.clone();
        return array;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swap(array, i, random.nextInt(array.length - i) + i);
        }
        return array;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
