package com.zjj.nowcoder.tx.ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine().trim());
            map.put(1, 1);
            map.put(2, 2);
            map.put(3, 2);
            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine().trim());
                writer.write(String.valueOf(calculate(n)));
                writer.newLine();
            }

        }
    }

    private static int calculate(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if ((n % 2) == 0 && (n % 3) == 0) {
            int two = calculate(n / 2);
            int three = calculate(n / 3);
            map.put(n, Math.min(two, three) + 1);
            return Math.min(two, three) + 1;
        }
        if ((n % 2) == 0) {
            int two = calculate(n / 2);
            int minus = calculate(n - 1);
            map.put(n, Math.min(two, minus) + 1);
            return Math.min(two, minus) + 1;
        }
        if ((n % 3) == 0) {
            int three = calculate(n / 3);
            int minus = calculate(n - 1);
            map.put(n, Math.min(three, minus) + 1);
            return Math.min(three, minus) + 1;
        }
        int minus = calculate(n - 1);
        map.put(n, minus + 1);
        return minus + 1;
    }
}
