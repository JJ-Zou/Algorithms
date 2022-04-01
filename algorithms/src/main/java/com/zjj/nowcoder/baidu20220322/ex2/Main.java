package com.zjj.nowcoder.baidu20220322.ex2;

import java.util.*;

public class Main {
    static class Pair {
        int left;
        int right;
        int s;

        public Pair(int left, int right, int s) {
            this.left = left;
            this.right = right;
            this.s = s;
        }
    }

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in);) {
            final char[] ch = scanner.nextLine().toCharArray();
            int len = ch.length;
            Map<Integer, Pair> map = new HashMap<>();
            Deque<Pair> deque = new ArrayDeque<>();
            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += ch[i] - '0';
            }
            deque.addLast(new Pair(0, len - 1, sum));
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    final Pair pair = deque.pollFirst();
                    int left = pair.left;
                    int right = pair.right;
                    int s = pair.s;
                    final Pair leftPair = new Pair(left + 1, right, s - (ch[left] - '0'));
                    if (map.containsKey(leftPair.s)) {
                        final Pair existPair = map.get(leftPair.s);
                        System.out.println((existPair.left + 1) + " " + (existPair.right + 1) + " " + (leftPair.left + 1) + " " + (leftPair.right + 1));
                        return;
                    } else {
                        map.put(leftPair.s, leftPair);
                        deque.addLast(leftPair);
                    }
                    final Pair rightPair = new Pair(left, right - 1, s - (ch[right] - '0'));
                    if (map.containsKey(rightPair.s)) {
                        final Pair existPair = map.get(rightPair.s);
                        System.out.println((existPair.left + 1) + " " + (existPair.right + 1) + " " + (rightPair.left + 1) + " " + (rightPair.right + 1));
                        return;
                    } else {
                        map.put(rightPair.s, rightPair);
                        deque.addLast(rightPair);
                    }
                }
            }
        }
    }
}
