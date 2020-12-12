package com.zjj.Leetcode.Leetcode223;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        if (E >= C || G <= A || F >= D || H <= B) {
            return area1 + area2;
        }
        if (E >= A && F >= B && G <= C && H <= D) {
            return area1;
        }
        if (E <= A && F <= B && G >= C && H >= D) {
            return area2;
        }
        int cross = (Math.min(D, H) - Math.max(B, F)) * (Math.min(C, G) - Math.max(A, E));
        return area1 + area2 - cross;
    }
}
