package com.zjj.meituan.q4;

import java.io.*;
import java.util.Arrays;

public class Main {
    private static StringBuilder str = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] split = reader.readLine().trim().split(" +");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            if (m == 1) {
                writer.write(String.valueOf(1));
                return;
            }
            if (m == 2) {
                writer.write(String.valueOf(2));
                return;
            }
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                arr[i] = Arrays.stream(reader.readLine().trim().split(" +"))
                        .mapToInt(Integer::parseInt).toArray();
            }
            int[] dp = new int[m];
            dp[0] = 1;
            str.append(1);
            dp[1] = 2;
            str.append(2);
            int res = 0;
            int cycle = 0;
            for (int i = 2; i < m; i++) {
                dp[i] = arr[dp[i - 1] - 1][dp[i - 2] - 1];
                if (str.toString().contains(dp[i - 1] + "" + dp[i - 2] + "" + dp[i])) {
                    cycle = i + 1;
                    break;
                }
                str.append(dp[i]);
            }
            writer.write(String.valueOf(dp[(m - 1) % cycle]));
        }
    }


}
