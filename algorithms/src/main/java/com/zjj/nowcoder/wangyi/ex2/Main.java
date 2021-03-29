package com.zjj.nowcoder.wangyi.ex2;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" +"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
    }
}
