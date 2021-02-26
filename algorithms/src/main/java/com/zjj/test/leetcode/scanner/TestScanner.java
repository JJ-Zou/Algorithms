package com.zjj.test.leetcode.scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (; ; ) {
            System.out.println(LocalDateTime.now().format(formatter));
            String input = scanner.nextLine();
            if (input == null || "".equals(input)) {
                continue;
            }
            String trim = input.trim();
            if (trim.charAt(0) != ':') {
                System.out.println("invalid");
            } else {
                System.out.println(input.substring(input.indexOf(':')));
            }
        }
    }
}
