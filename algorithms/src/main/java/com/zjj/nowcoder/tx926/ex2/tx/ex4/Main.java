package com.zjj.nowcoder.tx926.ex2.tx.ex4;

import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 1000000007;
    private static long res;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] arr = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            backtrack(new ArrayDeque<>(), 0, arr);
            writer.write(String.valueOf(res));
        }
    }

    private static void backtrack(Deque<Integer> deque, int cur, int[] arr) {
        if (deque.size() == 4) {
            long xor = 0;
            for (int num : deque) {
                xor ^= num;
            }
            res = (res % MOD + xor % MOD) % MOD;
            return;
        }
        for (int i = cur; i < arr.length; i++) {
            deque.addLast(arr[i]);
            backtrack(deque, i + 1, arr);
            deque.pollLast();
        }
    }
}
