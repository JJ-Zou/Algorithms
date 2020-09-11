package com.zjj.Leetcode.Leetcode79;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.exist(
                new char[][]{{'b', 'a', 'b', 'b', 'b', 'b', 'c', 'c'}, {'a', 'c', 'a', 'c', 'b', 'a', 'b', 'c'}, {'b', 'c', 'c', 'c', 'a', 'a', 'b', 'b'}, {'b', 'a', 'c', 'c', 'a', 'a', 'c', 'c'}, {'a', 'a', 'a', 'b', 'c', 'a', 'c', 'c'}, {'a', 'a', 'c', 'c', 'b', 'b', 'c', 'a'}, {'a', 'b', 'a', 'a', 'c', 'c', 'a', 'c'}, {'a', 'c', 'b', 'b', 'c', 'c', 'a', 'a'}, {'c', 'b', 'b', 'c', 'c', 'b', 'a', 'a'}, {'c', 'c', 'a', 'b', 'c', 'c', 'b', 'a'}, {'b', 'c', 'b', 'c', 'c', 'c', 'b', 'a'}, {'b', 'a', 'c', 'a', 'c', 'a', 'a', 'a'}, {'c', 'c', 'c', 'c', 'c', 'c', 'a', 'b'}}
                , "accbcaabbccabc"));
    }

    private boolean res;
    private boolean[][] visit;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (m * n < word.length()) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visit = new boolean[m][n];
                    dfs(board, word, i, j, 0);
                    if (res) {
                        return res;
                    }
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, String word, int r, int c, int index) {
        if (res) {
            return;
        }
        if (index == word.length()) {
            res = true;
            return;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return;
        }
        if (board[r][c] != word.charAt(index)) {
            return;
        }
        if (!visit[r][c]) {
            visit[r][c] = true;
            dfs(board, word, r, c + 1, index + 1);
            if (res) {
                return;
            }
            dfs(board, word, r + 1, c, index + 1);
            if (res) {
                return;
            }
            dfs(board, word, r, c - 1, index + 1);
            if (res) {
                return;
            }
            dfs(board, word, r - 1, c, index + 1);
            if (res) {
                return;
            }
            visit[r][c] = false;
        }
    }
}
