package com.zjj.leetcode.Leetcode85;

import java.util.Stack;

/**
 * 同84
 */
public class MaxRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int max = 0;
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = (chars[j] == '0')
                        ? 0
                        : (heights[j] + 1);
            }
            max = Math.max(max, maxRectangleOfRow(heights));
        }
        return max;
    }

    private int maxRectangleOfRow(int[] heights) {
        Stack<Integer> indexStack = new Stack<>();
        int curIndex = 0;
        int maxIndex = 0;
        int lastIndex = -1;
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            if (!indexStack.isEmpty() && heights[i] < heights[indexStack.peek()]) {
                maxIndex = indexStack.peek();
                while (!indexStack.isEmpty() && heights[i] < heights[indexStack.peek()]) {
                    curIndex = indexStack.pop();
                    lastIndex = indexStack.isEmpty() ? -1 : indexStack.peek();
                    max = Math.max(max, (maxIndex - lastIndex) * heights[curIndex]);
                }
            } else {
                max = Math.max(max, heights[i]);
            }
            indexStack.push(i);
        }
        maxIndex = indexStack.peek();
        while (!indexStack.isEmpty()) {
            curIndex = indexStack.pop();
            lastIndex = indexStack.isEmpty() ? -1 : indexStack.peek();
            max = Math.max(max, (maxIndex - lastIndex) * heights[curIndex]);
        }
        return max;
    }
}
