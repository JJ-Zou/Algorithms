package com.zz.nowcoder.bd3.ex1;

public class Solution {
    public static int countOne(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 1) {//奇数
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i / 2];
            }
            res += dp[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countOne(8));
    }
}
