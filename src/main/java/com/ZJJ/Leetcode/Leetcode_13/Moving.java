package com.ZJJ.Leetcode.Leetcode_13;

public class Moving {
    private int count;
    private boolean[][] flag;

    public int movingCount(int m, int n, int k) {
        count = 0;
        flag = new boolean[m][n];
        move(0, 0, m, n, k);
        return count;
    }

    private void move(int x, int y, int m, int n, int k) {
        int sum = sumBit(x) + sumBit(y);
        if (x < 0 || x >= m || y < 0 || y >= n || flag[x][y] || sum > k) {
            return;
        }
        flag[x][y] = true;
        count++;
        move(x + 1, y, m, n, k);
        move(x - 1, y, m, n, k);
        move(x, y + 1, m, n, k);
        move(x, y - 1, m, n, k);
    }

    private int sumBit(int num) {
        int sum = 0;
        while (num != 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }
}
