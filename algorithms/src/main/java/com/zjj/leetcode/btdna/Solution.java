package com.zjj.leetcode.btdna;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            for (; ; ) {
                System.out.println("please input two strings split if comma...");
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    return;
                }
                if (!input.contains(",")) {
                    continue;
                }
                String[] split = input.split(",");
                System.out.println(getMinCondition(split[0].toCharArray(),
                        split[1].toCharArray()));
            }
        }
    }

    private static int getMinCondition(char[] ch1, char[] ch2) {
        int len1 = ch1.length;
        int len2 = ch2.length;
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        int[][] dp = new int[len1 + 1][len2 + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[len1][len2];
    }
}
