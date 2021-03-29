package com.zjj.nowcoder.wangyi.ex4;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static Queue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine().trim());
            int y = Integer.parseInt(reader.readLine().trim());
            int[] x = Arrays.stream(reader.readLine().trim().split(" +"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int i = 0; i < x.length; i++) {
                sum = 0;
                backTrack(x, i, x.length);
            }
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur >= t) {
                    writer.write(String.valueOf(cur));
                    return;
                }
            }
            writer.write("0");
        }
    }

    static int sum;

    private static void backTrack(int[] x, int start, int n) {
        if (start >= n) {
            return;
        }
        sum += x[start];
        queue.add(sum);
        for (int i = start + 1; i < n; i++) {
            backTrack(x, i, n);
        }
        sum -= x[start];
    }
}
