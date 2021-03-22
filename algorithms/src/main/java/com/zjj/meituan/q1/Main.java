package com.zjj.meituan.q1;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] split = reader.readLine().trim().split(" +");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int[] arrayA = Arrays.stream(reader.readLine().trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            int[] arrayB = Arrays.stream(reader.readLine().trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            int res = m - 1;
            Arrays.sort(arrayB);
            int left = 0;
            int right = m - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                int[] temp = arrayA.clone();
                for (int i = 0; i < n; i++) {
                    temp[i] = (temp[i] + mid);
                }
                Arrays.sort(temp);
                if ((temp[0] - arrayB[0]) % m == 0) {
                    res = mid;
                    break;
                } else if ((temp[0] - arrayB[0]) < m) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            writer.write(String.valueOf(res));
        }
    }
}
