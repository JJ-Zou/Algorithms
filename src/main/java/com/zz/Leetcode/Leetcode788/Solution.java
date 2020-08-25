package com.zz.Leetcode.Leetcode788;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static Map<Integer, Integer> map;

    public static void main(String[] args) {
    }

    public static int rotatedDigits(int N) {
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 5);
        map.put(3, -1);
        map.put(4, -1);
        map.put(5, 2);
        map.put(6, 9);
        map.put(7, -1);
        map.put(8, 8);
        map.put(9, 6);
        return -1;
    }

    private static boolean goodNum(int n) {
        int cur = n;
        int mod = n;
        int mask = 1;
        int after = 0;
        while (cur > 9) {
            mod = cur % 10;
            mod = map.get(mod);
            if (mod == -1) {
                return false;
            }
            after += mask * mod;
            mask *= 10;
            cur = cur / 10;
        }
        mod = map.get(cur);
        if (mod == -1) {
            return false;
        }
        after += mask * mod;
        return after != n;
    }
}
