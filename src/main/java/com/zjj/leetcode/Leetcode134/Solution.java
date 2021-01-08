package com.zjj.leetcode.Leetcode134;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int left = 0;
        int sumLeft = 0;
        int res = 0;
        int diff = 0;
        for (int i = 0; i < n; i++) {
            left = gas[i] - cost[i];
            diff += left;
            if (sumLeft + left >= 0) {
                sumLeft += left;
            } else {
                res = i + 1;
                sumLeft = 0;
            }
        }
        return diff >= 0 ? res : -1;
    }
}
