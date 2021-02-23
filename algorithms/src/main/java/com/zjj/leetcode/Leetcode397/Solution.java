package com.zjj.leetcode.Leetcode397;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().integerReplacement(2147483647));
    }


    private Map<Integer, Integer> map = new HashMap<>();

    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if ((n & 1) == 0) {
            if (!map.containsKey(n >>> 1)) {
                map.put(n >>> 1, integerReplacement(n >>> 1));
            }
            return 1 + map.get(n >>> 1);
        }
        if (!map.containsKey(n - 1)) {
            map.put(n - 1, integerReplacement(n - 1));
        }
        if (!map.containsKey(n + 1)) {
            map.put(n + 1, integerReplacement(n + 1));
        }
        return 1 + Math.min(map.get(n - 1), map.get(n + 1));
    }

    public int integerReplacementSlow(int n) {
        if (n == 1) {
            return 0;
        }
        if ((n & 1) == 0) {
            return 1 + integerReplacement(n >>> 1);
        }
        return 1 + Math.min(integerReplacement(n - 1), integerReplacement(n + 1));
    }
}