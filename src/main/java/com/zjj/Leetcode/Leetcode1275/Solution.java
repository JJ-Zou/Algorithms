package com.zjj.Leetcode.Leetcode1275;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] moves = new int[][]{{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}};
        System.out.println(solution.tictactoe(moves));
    }

    public String tictactoe(int[][] moves) {
        int len = moves.length;
        int[][] cb = new int[3][3];
        for (int i = 0; i < len; i += 2) {
            cb[moves[i][0]][moves[i][1]] = 1;
            if (i + 1 < len) {
                cb[moves[i + 1][0]][moves[i + 1][1]] = 2;
            }
        }
        int r = moves[len - 1][0];
        int c = moves[len - 1][1];
        if ((cb[(r + 1) % 3][c] == cb[r][c] && cb[(r + 2) % 3][c] == cb[r][c])
                || (cb[r][(c + 1) % 3] == cb[r][c] && cb[r][(c + 2) % 3] == cb[r][c])
                || (r == c && cb[(r + 1) % 3][(c + 1) % 3] == cb[r][c]
                && cb[(r + 2) % 3][(c + 2) % 3] == cb[r][c])
                || (r + c == 2 && cb[(r + 1) % 3][(c + 2) % 3] == cb[r][c]
                && cb[(r + 2) % 3][(c + 1) % 3] == cb[r][c])) {
            return cb[r][c] == 1 ? "A" : "B";
        }
        return len == 9 ? "Draw" : "Pending";
    }
}
