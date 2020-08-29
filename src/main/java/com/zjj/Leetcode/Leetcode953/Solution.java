package com.zjj.Leetcode.Leetcode953;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAlienSorted(new String[]{"kuvp", "q"},
                "ngxlkthsjuoqcpavbfdermiywz"));
    }

    Map<Character, Integer> map = new HashMap<>();

    public boolean isAlienSorted(String[] words, String order) {
        int orderLen = order.length();
        for (int i = 0; i < orderLen; i++) {
            map.put(order.charAt(i), i);
        }
        int len = words.length;
        for (int i = 0; i < len - 1; i++) {
            if (!dicSort(words[i], words[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private boolean dicSort(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len = Math.min(len1, len2);
        int cur = 0;
        int order1 = map.get(s1.charAt(cur));
        int order2 = map.get(s2.charAt(cur++));
        boolean eq = (order1 == order2);
        while (eq && cur < len) {
            order1 = map.get(s1.charAt(cur));
            order2 = map.get(s2.charAt(cur++));
            eq = (order1 == order2);
        }
        return order1 < order2 || (order1 == order2 && len1 <= len2);
    }
}
