package com.zjj.nowcoder.aiqiyi0822.ex2;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String line = reader.readLine();
            if (line == null || line.length() == 0) {
                writer.write(String.valueOf(0));
                writer.newLine();
                return;
            }
            long[] arr = Arrays.stream(line.split(", *"))
                    .mapToLong(Long::parseLong)
                    .toArray();
            writer.write(String.valueOf(cal(arr)));
            writer.newLine();
        }
    }

    private static long cal(long[] arr) {
        int len = arr.length;
        long[] diff = new long[len - 1];
        for (int i = 0; i < len - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }
        long res = 0;
        long add = diff[0];
        int cur = 1;
        boolean up = true;
        for (int i = 1; i < len - 1; i++) {
            if (diff[i] > 0) {
                up = true;
                break;
            }
            if (diff[i] < 0) {
                up = false;
                break;
            }
        }
        while (cur < len - 1) {
            if (up) {
                if (diff[cur] < 0) {
                    res = Math.max(res, add);
                    add = diff[cur];
                } else {
                    add += diff[cur];
                }
            } else {
                if (diff[cur] > 0) {
                    res = Math.max(res, -add);
                    add = diff[cur];
                } else {
                    add += diff[cur];
                }
            }
            cur++;
        }
        return Math.max(res, Math.abs(add));
    }
}
