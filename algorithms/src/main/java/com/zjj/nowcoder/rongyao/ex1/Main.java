package com.zjj.nowcoder.rongyao.ex1;

import java.io.*;
import java.util.Arrays;
import java.util.TreeMap;
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                TreeMap<Integer, Integer> map = new TreeMap<>();
                int[] arr = Arrays.stream(reader.readLine().trim().split(" +"))
                        .mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < 10; j++) {
                    map.put((1 << (j + 1)), arr[j]);
                }
                int count = 0;
                int sum = 0;
                while (sum < 2048) {
                    int key = map.lastKey();
                    if (2048 - sum < key) {
                        map.remove(key);
                        continue;
                    }
                    sum += key;
                    map.put(key, map.get(key) - 1);
                    if (map.get(key) == 0) {
                        map.remove(key);
                    }
                    count++;
                }
                writer.write(String.valueOf(count - 1));
                writer.newLine();
            }
        }
    }
}
