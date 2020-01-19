package com.ZJJ.Leetcode.Leetcode519;

import java.util.*;

class Solution {
    private int[][] matrix;
    private Random random = new Random();
    private Set<Integer> set;
    private int row;
    private int col;
    public Solution(int n_rows, int n_cols) {
        row = n_rows;
        col = n_cols;
        matrix = new int[row][col];
        set = new HashSet<>();
        for(int i = 0;i < row * col;i ++) {
            set.add(i);
        }
    }

    public int[] flip() {
        int index = 0;
        do {
            index = index = (int) (Math.random() * row * col);
        }while(!set.remove(index));
        matrix[index / col][index % col] = 1;
        return new int[]{index / col,index % col};
    }

    public void reset() {
        matrix = new int[row][col];
        for(int i = 0;i < row * col;i ++) {
            set.add(i);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(1,2);
        System.out.println(Arrays.deepToString(solution.matrix));
        System.out.println(solution.set);
        solution.flip();
        System.out.println(Arrays.deepToString(solution.matrix));
        System.out.println(solution.set);
        solution.flip();
        System.out.println(Arrays.deepToString(solution.matrix));
        System.out.println(solution.set);
        solution.reset();
        System.out.println(Arrays.deepToString(solution.matrix));
        System.out.println(solution.set);
    }
}
