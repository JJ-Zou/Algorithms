package com.zz.Leetcode.Leetcode289;

public class GameOfLife {
    public void gameOfLife(int[][] board) {
        //00 0   [0,3,0]
        //01 2   [2,0,1]
        //10 3   [3,1,1]
        //11 1   [0,2,0]
        //(i-1,j-1) (i-1,j) (i-1,j+1)
        //(i,j-1)   (i,j)   (i,j+1)
        //(i+1,j-1) (i+1,j) (i+1,j+1)
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                count = 0;
                if (i - 1 >= 0) {
                    if (board[i - 1][j] == 1 || board[i - 1][j] == 3) {
                        count++;
                    }
                    if (j - 1 >= 0) {
                        if (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 3) {
                            count++;
                        }
                    }
                    if (j + 1 < board[0].length) {
                        if (board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 3) {
                            count++;
                        }
                    }
                }
                if (j - 1 >= 0) {
                    if (board[i][j - 1] == 1 || board[i][j - 1] == 3) {
                        count++;
                    }
                }
                if (j + 1 < board[0].length) {
                    if (board[i][j + 1] == 1 || board[i][j + 1] == 3) {
                        count++;
                    }
                }
                if (i + 1 < board.length) {
                    if (board[i + 1][j] == 1 || board[i + 1][j] == 3) {
                        count++;
                    }
                    if (j - 1 >= 0 && (board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 3)) {
                        count++;
                    }
                    if (j + 1 < board[0].length && (board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 3)) {
                        count++;
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    board[i][j] = 3;
                } else if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }
}
