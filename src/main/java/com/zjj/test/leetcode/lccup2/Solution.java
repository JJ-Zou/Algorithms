package com.zjj.test.leetcode.lccup2;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchRight(new int[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, 2));
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int count = 0;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int len = staple.length;
        for(int i = 0; i < len && staple[i] < x; i++) {
            if(staple[i] > x) {
                break;
            }
            int target = x - staple[i];
            count += searchRight(drinks, target) + 1;
        }
        return count;
    }
    public int searchRight(int[] drinks, int target) {
        int len = drinks.length;
        int left = 0;
        int right = len - 1;
        while(left <= right) {
            int mid = (left + right) >>> 1;
            if(drinks[mid] <= target && (mid == right || drinks[mid + 1] > target)) {
                return mid;
            } else if(drinks[mid] <= target) {
                left = mid + 1;
            } else if(drinks[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }
}
