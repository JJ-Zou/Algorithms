package com.zjj.nowcoder.ali49.ex1;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine().trim());
                int[] l = new int[n];
                int[] r = new int[n];
                for (int j = 0; j < n; j++) {
                    String[] pair = reader.readLine().trim().split(" +");
                    l[j] = Integer.parseInt(pair[0]);
                    r[j] = Integer.parseInt(pair[1]);
                }
                int[] times = time(n, l, r);
                for (int time : times) {
                    writer.write(time + " ");
                }
                writer.newLine();
            }
        }
    }

    private static int[] time(int n, int[] l, int[] r) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (l[o1] == l[o2]) {
                return o1 - o2;
            }
            return l[o1] - l[o2];
        });
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        int[] res = new int[n];
        int time = l[queue.peek()];
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (r[index] < time) {
                res[index] = 0;
            } else {
                if (l[index] > time) {
                    time = l[index];
                }
                res[index] = time++;
            }
        }
        return res;
    }
}
