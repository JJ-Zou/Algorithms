package com.zjj.Leetcode.Leetcode274;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int hIndex(int[] citations) {
        int len = citations.length;
        if (len == 0) {
            return 0;
        }
        int[] count = new int[len + 1];
        for (int citation : citations) {
            count[(Math.min(citation, len))]++;
        }
        int times = len;
        for (int i = count[times]; i < times; i += count[times]) {
            times--;
        }
        return times;
    }
}
