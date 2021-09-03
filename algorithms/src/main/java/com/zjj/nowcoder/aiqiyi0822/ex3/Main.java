package com.zjj.nowcoder.aiqiyi0822.ex3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().split("=")[1]);
            writer.write(cal(n).toString());
            writer.newLine();
        }
    }

    private static List<List<String>> cal(int n) {
        List<List<String>> res = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        int[] queue = new int[n];
        backtrack(res, queue, 0, n, cols, left, right);
        return res;
    }

    private static void backtrack(List<List<String>> res, int[] queue, int cur, int n,
                                  Set<Integer> cols, Set<Integer> left, Set<Integer> right) {
        if (cur == n) {
            res.add(fromQueue(queue));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (cols.contains(i)) {
                continue;
            }
            if (left.contains(cur - i)) {
                continue;
            }
            if (right.contains(cur + i)) {
                continue;
            }
            queue[cur] = i;
            cols.add(i);
            left.add(cur - i);
            right.add(cur + i);
            backtrack(res, queue, cur + 1, n, cols, left, right);
            cols.remove(i);
            left.remove(cur - i);
            right.remove(cur + i);
        }
    }

    private static List<String> fromQueue(int[] queue) {
        List<String> res = new ArrayList<>();
        int n = queue.length;
        for (int k : queue) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (k == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}
