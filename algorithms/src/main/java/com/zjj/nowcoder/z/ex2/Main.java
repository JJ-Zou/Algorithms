package com.zjj.nowcoder.z.ex2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 1) {
            System.out.println(n);
            return;
        }
        scanner.nextLine();
        String str = scanner.nextLine();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!stack.isEmpty() && stack.peek() + (str.charAt(i) - '0') == 10) {
                stack.pop();
            } else {
                stack.push(str.charAt(i) - '0');
            }
        }
        System.out.println(stack.size());
    }
}
