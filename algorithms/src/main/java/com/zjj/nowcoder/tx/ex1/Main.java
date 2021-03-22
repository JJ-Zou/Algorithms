package com.zjj.nowcoder.tx.ex1;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] split = reader.readLine().trim().split(" +");
            int n = Integer.parseInt(split[0]);
            int w = Integer.parseInt(split[1]);
            int[][] arr = new int[n][2];
            int min = 0;
            int max = 0;
            int[] diff = new int[n];
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Arrays.stream(reader.readLine().trim().split(" +"))
                        .mapToInt(Integer::parseInt).toArray();
                min += arr[i][0];
                max += arr[i][1];
                diff[i] = arr[i][1] - arr[i][0];
                res[i] = arr[i][1];
            }
            Arrays.sort(diff);
            int curr = n - 1;
            while (max > w) {
                if (max - diff[curr] < w) {
                    res[curr] -= max - w;
                } else {
                    max -= curr;
                    res[curr] = diff[curr];
                }
            }
            Arrays.sort(res);
            writer.write(String.valueOf(res[(n - 1) / 2]));
            writer.newLine();
        }
    }

}
