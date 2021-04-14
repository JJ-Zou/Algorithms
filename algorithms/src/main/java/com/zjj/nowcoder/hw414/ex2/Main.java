package com.zjj.nowcoder.hw414.ex2;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] arr = Arrays.stream(reader.readLine().trim().split(" +"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (n == 1) {
                writer.write(String.valueOf(0));
                return;
            }
            int[] dp = new int[n];
            Arrays.fill(dp, n);
            dp[0] = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] + j >= i) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
            writer.write(String.valueOf(dp[n - 1]));
        }
    }
}