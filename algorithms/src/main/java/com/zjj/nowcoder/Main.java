package com.zjj.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            String mask = scanner.nextLine();
            String ip1 = scanner.nextLine();
            String ip2 = scanner.nextLine();
            System.out.println(judge(mask, ip1, ip2));
        } while (scanner.hasNextLine());
    }

    private static int judge(String mask, String ip1, String ip2) {
        try {
            int[] maskArr = Arrays.stream(mask.trim().split("\\.")).mapToInt(Integer::parseInt).toArray();
            int[] ip1Arr = Arrays.stream(ip1.trim().split("\\.")).mapToInt(Integer::parseInt).toArray();
            int[] ip2Arr = Arrays.stream(ip2.trim().split("\\.")).mapToInt(Integer::parseInt).toArray();
            for (int n : maskArr) {
                if (n < 0 || n > 255) {
                    return 1;
                }
            }
            for (int n : ip1Arr) {
                if (n < 0 || n > 255) {
                    return 1;
                }
            }
            for (int n : ip2Arr) {
                if (n < 0 || n > 255) {
                    return 1;
                }
            }
            for (int i = 0; i < 4; i++) {
                if ((ip1Arr[i] & maskArr[i]) != (ip2Arr[i] & maskArr[i])) {
                    return 2;
                }
            }
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
}
