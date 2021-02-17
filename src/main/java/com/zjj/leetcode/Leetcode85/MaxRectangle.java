package com.zjj.leetcode.Leetcode85;

import java.util.Stack;

/**
 * 同84
 */
public class MaxRectangle {
    public static void main(String[] args) {
        System.out.println(new MaxRectangle().maximalRectangle0(new char[][]{{'0', '1'}}));
    }

    public int maximalRectangle0(char[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = i; k < matrix.length; k++) {
                    for (int m = j; m < matrix[0].length; m++) {
                        if (valid(matrix, new int[]{i, j}, new int[]{k, m})) {
                            res = Math.max(res, (k - i + 1) * (m - j + 1));
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean valid(char[][] matrix, int[] point1, int[] point2) {
        for (int i = point1[0]; i <= point2[0]; i++) {
            for (int j = point1[1]; j <= point2[1]; j++) {
                if (matrix[i][j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }

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
