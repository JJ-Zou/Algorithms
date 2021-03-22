package com.zjj.meituan.q2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int res = 0;
            String line = reader.readLine();
            int len = line.length();
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.put(line.charAt(i), i);
            }
            String word = reader.readLine();
            if (word.length() == 1) {
                writer.write(String.valueOf(25));
                return;
            }
            int start = 1;
            while (start < word.length()) {
                int cmp = map.get(word.charAt(start)) - map.get(word.charAt(start - 1));
                if (cmp == 0) {
                    res += 25;
                    start++;
                } else if (cmp > 0) {
                    res += cmp - 1;
                    start++;
                } else {
                    res += cmp + map.get(line.charAt(len - 1)) - map.get(line.charAt(0));
                    start++;
                }
            }
            res += map.get(line.charAt(len - 1)) - map.get(word.charAt(start - 1));
            writer.write(String.valueOf(res));
        }
    }
}
