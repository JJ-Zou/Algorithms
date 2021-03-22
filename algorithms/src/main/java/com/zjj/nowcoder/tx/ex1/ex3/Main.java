package com.zjj.nowcoder.tx.ex1.ex3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            Integer[][] array = new Integer[n][];
            for (int i = 0; i < n; i++) {
                array[i] = Arrays.stream(reader.readLine().trim().split(" +"))
                        .skip(1)
                        .map(Integer::parseInt)
                        .sorted()
                        .toArray(Integer[]::new);
            }
            int q = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < q; i++) {
                int[] line = Arrays.stream(reader.readLine().trim().split(" +"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int length = line.length;
                writer.write(String.valueOf(findK(array, Arrays.copyOfRange(line, 1, 1 + line[0]), line[length - 1])));
                writer.newLine();
            }
        }
    }

    private static int findK(Integer[][] array, int[] b, int k) {
        List<Integer> list = new ArrayList<>();
        for (int index : b) {
            Integer[] arr = array[index - 1];
            list.addAll(Arrays.stream(arr).collect(Collectors.toList()));
        }
        return list.stream().sorted().skip(k - 1).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
