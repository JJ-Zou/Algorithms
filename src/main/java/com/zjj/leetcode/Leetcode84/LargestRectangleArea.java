package com.zjj.Leetcode.Leetcode84;

import java.util.Stack;

/**
 * 利用一个单调不减的栈，当一个不小于栈顶的数尝试进入时，直接入栈
 * 当一个小于栈顶的数尝试进入时，栈弹出元素，直到为空或者当前数小于栈顶元素为止，当前数入栈
 * 依次弹出元素直到栈为空
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> maxIndex = new Stack<>();
        int max = 0;
        int curMax = 0;
        int cur = 0;
        int last = -1;
        for (int i = 0; i < heights.length; i++) {
            if (!maxIndex.isEmpty() && heights[i] < heights[maxIndex.peek()]) {
                curMax = maxIndex.peek();
                while (!maxIndex.isEmpty() && heights[i] < heights[maxIndex.peek()]) {
                    cur = maxIndex.pop();
                    last = maxIndex.isEmpty() ? -1 : maxIndex.peek();
                    max = Math.max(max, (curMax - last) * heights[cur]);
                }
            } else {
                max = Math.max(max, heights[i]);
            }
            maxIndex.push(i);
        }
        curMax = maxIndex.peek();
        while (!maxIndex.isEmpty()) {
            cur = maxIndex.pop();
            last = maxIndex.isEmpty() ? -1 : maxIndex.peek();
            max = Math.max(max, (curMax - last) * heights[cur]);
        }
        return max;
    }
}
