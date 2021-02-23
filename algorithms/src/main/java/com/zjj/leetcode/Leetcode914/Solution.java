package com.zjj.leetcode.Leetcode914;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int gcd = -1;
        for (int key : map.keySet()) {
            if (gcd == -1) {
                gcd = map.get(key);
            } else {
                gcd = gcd(gcd, map.get(key));
            }
        }
        return gcd >= 2;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (b > a) {
            return gcd(b, a);
        }
        return gcd(b, a % b);
    }
}
