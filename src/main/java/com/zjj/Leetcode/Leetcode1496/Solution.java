package com.zjj.Leetcode.Leetcode1496;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPathCrossing("NENSNENENENNSNSNSNENNE"));
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean isPathCrossing(String path) {
        Set<Pair> set = new HashSet<>();
        Pair pair = new Pair(0, 0);
        for (char c : path.toCharArray()) {
            set.add(new Pair(pair.x, pair.y));
            switch (c) {
                case 'N':
                    pair.x++;
                    break;
                case 'S':
                    pair.x--;
                    break;
                case 'E':
                    pair.y++;
                    break;
                case 'W':
                    pair.y--;
                    break;
            }
            if (set.contains(new Pair(pair.x, pair.y))) {
                return true;
            }
        }
        return false;
    }
}
