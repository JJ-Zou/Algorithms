package com.zjj.Leetcode.Leetcode42;

/**
 * 双指针
 * 保存左指针左侧，右指针右侧的最大值
 * 两个最大值的最小值与当前指针处的差值为该处接的雨水
 */
public class RainWater {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int left = 1;
        int right = height.length - 2;
        int maxLeft = height[0];
        int maxRight = height[height.length - 1];
        int sum = 0;
        int t = 0;
        while (left <= right) {
            if (maxLeft <= maxRight) {
                t = Math.min(maxLeft, maxRight) - height[left];
                if (t > 0) {
                    sum += t;
                }
                maxLeft = Math.max(maxLeft, height[left]);
                left++;
            } else {
                t = Math.min(maxLeft, maxRight) - height[right];
                if (t > 0) {
                    sum += t;
                }
                maxRight = Math.max(maxRight, height[right]);
                right--;
            }
        }
        return sum;
    }
}
