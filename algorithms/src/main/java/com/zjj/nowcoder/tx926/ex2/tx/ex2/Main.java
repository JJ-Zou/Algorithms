package com.zjj.nowcoder.tx926.ex2.tx.ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] strs = reader.readLine().split(" ");
            int n = Integer.parseInt(strs[0]);
            int m = Integer.parseInt(strs[1]);
            int[] arr1 = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int[] arr2 = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            int left = 0;
            int right = m;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (valid(arr1.clone(), arr2.clone(), mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            writer.write(String.valueOf(left));
        }
    }

    private static boolean valid(int[] arr1, int[] arr2, int n) {
        int left = 0;
        int right = arr1.length - 1;
        int attackIndex = 0;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr1[mid] == n || (arr1[mid] < n && arr1[mid + 1] > n)) {
                attackIndex = mid;
                break;
            } else if (arr1[mid] > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

}
