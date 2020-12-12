package com.zjj.Leetcode.Leetcode1175;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numPrimeArrangements(5));
    }

    public int numPrimeArrangements(int n) {
        // (a * b) mod c = (a mod c * b mod c) mod c
        Set<Integer> set = new HashSet<>();
        set.add(1);
        for (int i = 2; i <= (n >> 1); i++) {
            int times = 2;
            while (i * times <= n) {
                set.add(i * times);
                times++;
            }
        }
        int fac = set.size();
        long res = 1;
        for (int i = 1; i <= fac; i++) {
            res = (i * res) % 1000000007;
        }
        for (int i = 1; i <= n - fac; i++) {
            res = (i * res) % 1000000007;
        }
        return (int) res;
    }
}
