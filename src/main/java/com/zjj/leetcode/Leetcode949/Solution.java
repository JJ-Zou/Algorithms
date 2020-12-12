package com.zjj.Leetcode.Leetcode949;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    boolean[] flag = new boolean[4];

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestTimeFromDigits(new int[]{1, 2, 3, 4}));
    }

    public String largestTimeFromDigits(int[] A) {
        Queue<String> queue = new PriorityQueue<>((o1, o2) -> {
            int h1 = Integer.parseInt(o1.substring(0, 2));
            int h2 = Integer.parseInt(o2.substring(0, 2));
            int m1 = Integer.parseInt(o1.substring(2));
            int m2 = Integer.parseInt(o2.substring(2));
            return h2 > h1 ? 1 : (h2 == h1 ? (Integer.compare(m2, m1)) : -1);
        });
        back(A, new StringBuilder(), queue);
        if (queue.isEmpty()) {
            return "";
        }
        String s = queue.peek();
        return s.substring(0, 2) + ":" + s.substring(2);
    }

    private void back(int[] A, StringBuilder sb, Queue<String> queue) {
        if (sb.length() == 4) {
            if (valid(sb.toString())) {
                queue.add(sb.toString());
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (flag[i]) {
                continue;
            }
            flag[i] = true;
            sb.append(A[i]);
            back(A, sb, queue);
            sb.deleteCharAt(sb.length() - 1);
            flag[i] = false;
        }
    }

    private boolean valid(String s) {
        int num = Integer.parseInt(s);
        int h = num / 100;
        int m = num % 100;
        return h <= 23 && m <= 59;
    }
}
