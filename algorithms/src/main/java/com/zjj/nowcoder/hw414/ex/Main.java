package com.zjj.nowcoder.hw414.ex;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String input = reader.readLine().trim();
            if (input.length() == 0) {
                writer.write("");
                return;
            }
            char[] ch = input.toCharArray();
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : ch) {
                if (c == ')') {
                    StringBuilder sb = new StringBuilder();
                    while (deque.peek() != '(') {
                        char pop = deque.pop();
                        sb.append(pop);
                    }
                    deque.pop();
                    if (deque.isEmpty()) {
                        if (sb.length() == 0) {
                            writer.write("");
                            return;
                        }
                        for (char cha : sb.toString().toCharArray()) {
                            writer.write(String.valueOf(cha));
                        }
                        return;
                    }
                    for (char cha : sb.toString().toCharArray()) {
                        deque.push(cha);
                    }
                } else {
                    deque.push(c);
                }
            }
            if (deque.isEmpty()) {
                writer.write("");
                return;
            }
            for (char c : deque) {
                writer.write(String.valueOf(c));
            }
        }
    }
}
