package com.zjj.nowcoder.tx926.ex2.tx.ex3;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] arr = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr);
            long res = 0;
            int mod = 1000000007;
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = dp[i - 1] * 2 % mod;
            }
            for (int i = 0; i < n; i++) {
                res = (res % mod + arr[i] * dp[n - 1 - i] % mod + arr[n - 1 - i] * dp[n - 1 - i] % mod) % mod;
            }
            writer.write(String.valueOf(res));
        }
    }
}
