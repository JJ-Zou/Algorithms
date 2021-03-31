package com.zjj.nowcoder.baidu.ex2;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] firstLine = reader.readLine().trim().split(" +");
            int n = Integer.parseInt(firstLine[0]);
            int p = Integer.parseInt(firstLine[1]);
            long[] array = Arrays.stream(reader.readLine().trim().split(" +"))
                    .mapToLong(Long::parseLong)
                    .sorted()
                    .toArray();
            Arrays.fill(array, 1000_000_000_00L);
            long sum = 0;
            for (long num : array) {
                sum += num;
            }
            String res;
            if (p == 50) {
                if ((sum & 1) == 0) {
                    writer.write(String.valueOf(sum / 2));
                    return;
                }
                writer.write(multi("50", String.valueOf(sum)) + "%");
                return;
            } else if (p > 50) {
                long halfSum = 0;
                for (int i = 0; i < n; i++) {
                    halfSum += array[i];
                }
                if (p == 100) {
                    writer.write(String.valueOf(sum - halfSum));
                    return;
                }
                res = add(multi(String.valueOf(100 - p), String.valueOf(halfSum)),
                        multi(String.valueOf(p), String.valueOf(sum - halfSum)));
            } else {
                long halfSum = 0;
                for (int i = 0; i < n; i++) {
                    halfSum += array[2 * i];
                }
                if (p == 0) {
                    writer.write(String.valueOf(halfSum));
                    return;
                }
                res = add(multi(String.valueOf(100 - p), String.valueOf(halfSum)),
                        multi(String.valueOf(p), String.valueOf(sum - halfSum)));
            }
            if (res.endsWith("00")) {
                writer.write(res.substring(0, res.length() - 2));
                return;
            }
            writer.write(res + "%");
        }
    }

    private static String multi(String s1, String s2) {
        if (s1.length() == 1) {
            return String.valueOf(Long.parseLong(s1) * Long.parseLong(s2));
        }
        long l2 = Long.parseLong(s2);
        int ten = s1.charAt(0) - '0';
        int one = s1.charAt(1) - '0';
        long tenMul = ten * l2;
        long oneMul = one * l2;
        String tempTem = tenMul + "0";
        if (one == 0) {
            return tempTem;
        }
        return add(tempTem, String.valueOf(oneMul));
    }

    private static String add(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int cur1 = len1 - 1;
        int cur2 = len2 - 1;
        int advance = 0;
        StringBuilder sb = new StringBuilder();
        while (cur1 >= 0 && cur2 >= 0) {
            int a = s1.charAt(cur1) - '0';
            int b = s2.charAt(cur2) - '0';
            int t = a + b + advance;
            sb.append(t % 10);
            advance = t / 10;
            cur1--;
            cur2--;
        }
        while (cur1 >= 0) {
            int a = s1.charAt(cur1) - '0';
            int t = a + advance;
            sb.append(t % 10);
            advance = t / 10;
            cur1--;
        }
        while (cur2 >= 0) {
            int b = s2.charAt(cur2) - '0';
            int t = b + advance;
            sb.append(t % 10);
            advance = t / 10;
            cur2--;
        }
        if (advance != 0) {
            sb.append(advance);
        }
        return sb.reverse().toString();
    }
}
