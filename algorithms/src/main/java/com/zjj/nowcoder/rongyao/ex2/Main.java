package com.zjj.nowcoder.rongyao.ex2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int line = Integer.parseInt(reader.readLine());
            Map<String, Integer> map = new HashMap<>();
            String res = "NA";
            for (int i = 0; i < line; i++) {
                String s = reader.readLine();
                String[] split = s.trim().split("=");
                String var = split[0].trim();
                String[] express = split[1].trim().split("\\+");
                int result = 0;
                for (String str : express) {
                    try {
                        result += Integer.parseInt(str);
                    } catch (Exception e) {
                        if (map.containsKey(str.trim())) {
                            result += map.get(str);
                        } else {
                            result = -1;
                            break;
                        }
                    }
                }
                if (result == -1) {
                    continue;
                }
                res = String.valueOf(result);
                map.put(var, result);
            }
            writer.write(res);
            writer.newLine();
        }
    }
}
