package com.zjj.nowcoder.wangyi.ex3;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" +"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Queue<Integer>[] queues = new PriorityQueue[6];
            for (int i = 0; i < 6; i++) {
                queues[i] = new PriorityQueue<>((i1, i2) -> nums[i2] - nums[i1]);
            }
            int sum = 0;
            boolean changed = false;
            for (int i = 0; i < n; i++) {
                int mod = (nums[i] % 6 + 6) % 6;
                if (mod == 0) {
                    if (nums[i] >= 0) {
                        changed = true;
                        sum += nums[i];
                    }
                } else {
                    queues[mod].add(i);
                }
            }
            for (int i = 1; i < 3; i++) {
                while (!queues[i].isEmpty() && !queues[6 - i].isEmpty()) {
                    int first = queues[i].poll();
                    int second = queues[6 - i].poll();
                    if (nums[first] + nums[second] >= 0) {
                        changed = true;
                        sum += nums[first] + nums[second];
                    }
                }
            }
            while (!queues[3].isEmpty()) {
                int first = queues[3].poll();
                if (queues[3].isEmpty()) {
                    break;
                }
                int second = queues[3].poll();
                if (nums[first] + nums[second] >= 0) {
                    changed = true;
                    sum += nums[first] + nums[second];
                }
            }
            writer.write(String.valueOf(changed ? sum : -1));
            writer.newLine();
        }
    }
}
