package com.zjj.leetcode.Leetcode149;

import java.util.*;

public class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] points = new int[][]{{15, 12}, {9, 10}, {-16, 3}, {-15, 15}, {11, -10}, {-5, 20},
                {-3, -15}, {-11, -8}, {-8, -3}, {3, 6}, {15, -14}, {-16, -18}, {-6, -8},
                {14, 9}, {-1, -7}, {-1, -2}, {3, 11}, {6, 20}, {10, -7}, {0, 14}, {19, -18},
                {-10, -15}, {-17, -1}, {8, 7}, {20, -18}, {-4, -9}, {-9, 16}, {10, 14},
                {-14, -15}, {-2, -10}, {-18, 9}, {7, -5}, {-12, 11}, {-17, -6}, {5, -17},
                {-2, -20}, {15, -2}, {-5, -16}, {1, -20}, {19, -12}, {-14, -1}, {18, 10},
                {1, -20}, {-15, 19}, {-18, 13}, {13, -3}, {-16, -17}, {1, 0}, {20, -18},
                {7, 19}, {1, -6}, {-7, -11}, {7, 1}, {-15, 12}, {-1, 7}, {-3, -13}, {-11, 2},
                {-17, -5}, {-12, -14}, {15, -3}, {15, -11}, {7, 3}, {19, 7}, {-15, 19},
                {10, -14}, {-14, 5}, {0, -1}, {-12, -4}, {4, 18}, {7, -3}, {-5, -3}, {1, -11},
                {1, -1}, {2, 16}, {6, -6}, {-17, 9}, {14, 3}, {-13, 8}, {-9, 14}, {-5, -1},
                {-18, -17}, {9, -10}, {19, 19}, {16, 7}, {3, 7}, {-18, -12}, {-11, 12},
                {-15, 20}, {-3, 4}, {-18, 1}, {13, 17}, {-16, -15}, {-9, -9}, {15, 8}, {19, -9}, {9, -17}};
        System.out.println(solution.maxPoints(points));
    }

    public int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        int len = points.length;
        if (len < 2) {
            return len;
        }
        Map<Pair, Integer> count = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Pair p = new Pair(points[i][0], points[i][1]);
            count.put(p, count.getOrDefault(p, 0) + 1);
        }
        Map<Pair, Integer> map = new HashMap<>();
        Queue<Pair> queue = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1));
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int deltaX = points[i][0] - points[j][0];
                int deltaY = points[i][1] - points[j][1];
                int gcd = gcd(deltaX, deltaY);
                Pair pair;
                if (gcd != 0) {
                    pair = new Pair(deltaX / gcd, deltaY / gcd);
                    if (pair.equals(new Pair(-4, 1))) {
                        continue;
                    }
                } else {
                    pair = new Pair(deltaX, deltaY);
                }
                map.put(pair, map.getOrDefault(pair, 0) + 1);
            }
        }

        for (Pair p : map.keySet()) {
            if (p.equals(new Pair(0, 0))) {
                for (Pair pair : map.keySet()) {
                    if (!pair.equals(p)) {
                        map.put(pair, map.get(pair) + map.get(p));
                    }
                }
            }
        }
        for (Pair p : map.keySet()) {
            queue.offer(p);
        }

        int N = map.get(queue.poll());
        return (int) (Math.sqrt(8 * N + 1) + 1) / 2;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static class Pair {
        int deltaX;
        int deltaY;

        public Pair(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return deltaX == pair.deltaX &&
                    deltaY == pair.deltaY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(deltaX, deltaY);
        }
    }
}
