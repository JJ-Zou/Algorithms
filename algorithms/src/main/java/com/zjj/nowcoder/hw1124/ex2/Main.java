package com.zjj.nowcoder.hw1124.ex2;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] str = reader.readLine().split(" ");
            int res = 0;
            int m = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);
            int[] masks = new int[m];
            int[] blacks = new int[m];
            for (int i = 0; i < m; i++) {
                String blackIp = reader.readLine();
                String[] split = blackIp.split("/");
                String[] ips = split[0].split("\\.");
                int count = Integer.parseInt(split[1]);
                masks[i] = 0xFFFFFFFF << (32 - count);
                int ip = Integer.parseInt(ips[0]) << 24 |
                        Integer.parseInt(ips[1]) << 16 |
                        Integer.parseInt(ips[2]) << 8 |
                        Integer.parseInt(ips[3]);
                blacks[i] = (ip & masks[i]);
            }
            for (int i = 0; i < n; i++) {
                String ip = reader.readLine();
                String[] ips = ip.split("\\.");
                int ipNum = Integer.parseInt(ips[0]) << 24 |
                        Integer.parseInt(ips[1]) << 16 |
                        Integer.parseInt(ips[2]) << 8 |
                        Integer.parseInt(ips[3]);
                for (int j = 0; j < m; j++) {
                    if (blacks[j] == (ipNum & masks[j])) {
                        writer.write(ip);
                        writer.newLine();
                        res++;
                        break;
                    }
                }
            }
            if (res == 0) {
                writer.write("null");
                writer.newLine();
            }
        }
    }
}