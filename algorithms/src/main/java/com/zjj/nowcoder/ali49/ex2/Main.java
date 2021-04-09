package com.zjj.nowcoder.ali49.ex2;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] pair = reader.readLine().trim().split(" +");
            int n = Integer.parseInt(pair[0]);
            int k = Integer.parseInt(pair[1]);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }
            for (int i = 0; i < k; i++) {
                pair = reader.readLine().trim().split(" +");
                int start = Integer.parseInt(pair[0]);
                int end = Integer.parseInt(pair[1]);
                int left = start - 1;
                int right = end - 1;
                while (left < right) {
                    swap(arr, left++, right--);
                }
            }
            for (int num : arr) {
                writer.write(num + " ");
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
