package com.zz.Leetcode.Leetcode13;

import java.util.HashMap;
import java.util.Map;

/**
 * 当前值小于后面一个值时，减去该值
 * 否则加上该值
 * 加上末尾字符的值
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int num = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                num -= map.get(s.charAt(i));
            } else {
                num += map.get(s.charAt(i));
            }
        }
        return num + map.get(s.charAt(s.length() - 1));
    }
}
