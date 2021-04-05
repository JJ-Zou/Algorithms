package com.zjj.nowcoder.z.ex3;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine().trim());
                int[] arr = Arrays.stream(reader.readLine().trim().split(" +"))
                        .mapToInt(Integer::parseInt)
                        .sorted()
                        .toArray();
                writer.write(String.valueOf(minTime(arr, n)));
                writer.newLine();
            }
        }
    }

    private static int minTime(int[] arr, int n) {
        int res = 0;
        int cur = n - 1;
        while (cur >= 0) {
            res += arr[cur];
            cur -= 2;
        }
        return res;
    }
}
