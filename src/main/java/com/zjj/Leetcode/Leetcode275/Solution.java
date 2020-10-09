package com.zjj.Leetcode.Leetcode275;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hIndex(new int[]{11, 15}));
    }

    // largest i: citations[i] > len - i - 1
    public int hIndex(int[] citations) {
        int len = citations.length;
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (citations[mid] > len - mid - 1 &&
                    (mid == 0 || citations[mid - 1] <= len - mid)) {
                return len - mid;
            } else if (citations[mid] > len - mid - 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return citations[left] > len - left - 1 ? len - left : 0;
    }
}
