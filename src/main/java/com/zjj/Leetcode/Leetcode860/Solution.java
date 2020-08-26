package com.zjj.Leetcode.Leetcode860;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5}));
    }

    public static boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int bill : bills) {
            if (bill == 10) {
                if (!map.containsKey(5) || map.get(5) == 0) {
                    return false;
                }
                map.put(5, map.get(5) - 1);
            } else if (bill == 20) {
                if (!map.containsKey(5) || map.get(5) == 0 ||
                        (map.get(5) < 3 && (!map.containsKey(10) || map.get(10) == 0))) {
                    return false;
                }
                if (map.containsKey(10) && map.get(10) > 0) {
                    map.put(10, map.get(10) - 1);
                } else {
                    map.put(5, map.get(5) - 1);
                    map.put(5, map.get(5) - 1);
                }
                map.put(5, map.get(5) - 1);
            }
            map.put(bill, map.getOrDefault(bill, 0) + 1);
        }
        return true;
    }

}
