package com.zjj.nowcoder.baidu.ex1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            if (n == 0) {
                writer.write("0");
                return;
            }
            char[] ch = reader.readLine().trim().toCharArray();
            int[] dp = new int[n];
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.putIfAbsent(ch[i], i);
            }
            for (int i = 1; i < n; i++) {
                dp[i] = dp[i - 1] + 1;
                if (map.containsKey(ch[i]) && map.get(ch[i]) != i) {
                    if (dp[i] < dp[map.get(ch[i])] + 1) {
                        map.put(ch[i], i);
                    } else {
                        dp[i] = dp[map.get(ch[i])] + 1;
                    }
                }
            }
            writer.write(String.valueOf(dp[n - 1]));
        }
    }
}
