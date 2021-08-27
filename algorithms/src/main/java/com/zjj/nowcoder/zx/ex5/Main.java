package com.zjj.nowcoder.zx.ex5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        System.out.println(step(s, t, 1, 1));
    }

    private static int step(int s, int t, int op, int step) {
        if(s == t) {
            return 0;
        }
        s += op * step;
        if (s > t) {
            return 1 + step(s, t, 1, 1);
        }
        op *= -1;
        step *= 2;
        return 1 + Math.min(step(s, t, op, step), step(s, t, 1, 1));
    }
}
