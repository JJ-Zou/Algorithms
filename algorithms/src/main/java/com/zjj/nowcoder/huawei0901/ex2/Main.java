package com.zjj.nowcoder.huawei0901.ex2;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            Map<String, List<String>> map = new HashMap<>();
            Map<String, List<String>> instanceMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String[] split = reader.readLine().split(" ");
                String s = split[0];
                String p = split[2];
                if ("subClassOf".equals(split[1])) {
                    if (!map.containsKey(p)) {
                        map.put(p, new ArrayList<>());
                    }
                    map.get(p).add(s);
                } else {
                    if (!instanceMap.containsKey(p)) {
                        instanceMap.put(p, new ArrayList<>());
                    }
                    instanceMap.get(p).add(s);
                }
            }
            String str = reader.readLine();
            TreeSet<String> set = new TreeSet<>();
            Deque<String> deque = new ArrayDeque<>();
            deque.addLast(str);
            while (!deque.isEmpty()) {
                String cur = deque.pollFirst();
                if (instanceMap.containsKey(cur)) {
                    set.addAll(instanceMap.get(cur));
                }
                if (map.containsKey(cur)) {
                    for (String s : map.get(cur)) {
                        deque.addLast(s);
                    }
                }
            }
            if (set.isEmpty()) {
                writer.write("empty");
            } else {
                String first = set.pollFirst();
                writer.write(first);
                for (String s : set) {
                    writer.write(" " + s);
                }
            }
        }
    }
}
