package com.zjj.Leetcode.Leetcode347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    private Map<Integer, Integer> map;
    private Random random;

    public int[] topKFrequent(int[] nums, int k) {
        map = new HashMap<>();
        random = new Random();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int len = map.size();
        int[] arr = new int[len];
        int index = 0;
        for (int key : map.keySet()) {
            arr[index++] = key;
        }
        int topK = findTopK(arr, 0, len - 1, len - k);
        return Arrays.copyOfRange(arr, topK, len);
    }

    private int findTopK(int[] arr, int start, int end, int k) {
        int index = partition(arr, start, end);
        if (index == k) {
            return index;
        } else if (index < k) {
            return findTopK(arr, index + 1, end, k);
        }
        return findTopK(arr, start, index - 1, k);
    }

    private int partition(int[] arr, int start, int end) {
        swap(arr, start, random.nextInt(end - start + 1) + start);
        int pivot = map.get(arr[start]);
        int left = start - 1;
        int right = end + 1;
        int cur = start + 1;
        while (cur < right) {
            int cmp = map.get(arr[cur]) - pivot;
            if (cmp < 0) {
                swap(arr, cur++, ++left);
            } else if (cmp > 0) {
                swap(arr, cur, --right);
            } else {
                cur++;
            }
        }
        return left + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
