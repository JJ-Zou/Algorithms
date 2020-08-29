package com.zjj.tianchi0829.sub2;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.makeEquilateralTriangle(new int[]{2, 3, 3, 5, 7, 7}));
    }
    public int makeEquilateralTriangle(int[] lengths) {
        // write your code here.
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int min = 2;
        for (int length : lengths) {
            if (map.containsKey(length * 2) || ((length & 1) == 0 && map.containsKey(length / 2))) {
                min = 1;
            }
            map.put(length, map.getOrDefault(length, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 3) {
                return 0;
            }
            if (map.get(key) == 2 && map.higherKey(key) != null) {
                min = 1;
            }
        }
        return min;
    }
}
