package com.zjj.leetcode.Leetcode529;

public class Solution {
    private final int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            dfs(board, x, y);
        }
        return board;
    }

    private void dfs(char[][] board, int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int row = x + dx[i];
            int col = y + dy[i];
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
                continue;
            }
            if (board[row][col] == 'M') {
                count++;
            }
        }
        if (count > 0) {
            board[x][y] = (char) (count + '0');
        } else {
            board[x][y] = 'B';
            for (int i = 0; i < 8; i++) {
                int row = x + dx[i];
                int col = y + dy[i];
                if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'E') {
                    continue;
                }
                dfs(board, row, col);
            }
        }
    }
}