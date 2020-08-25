package com.zz.Leetcode.Leetcode748;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
    }

    public static String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : licensePlate.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            } else if (ch >= 'A' && ch <= 'Z') {
                map.put((char) (ch - 'A' + 'a'),
                        map.getOrDefault((char) (ch - 'A' + 'a'), 0) + 1);
            }
        }
        String res = null;
        for (String word : words) {
            if (contains(new HashMap<>(map), word)) {
                if (res == null) {
                    res = word;
                } else if (word.length() < res.length()) {
                    res = word;
                }
            }
        }
        return res;
    }

    private static boolean contains(Map<Character, Integer> map, String word) {
        for (char ch : word.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) {
                    map.remove(ch);
                }
            }
        }
        return map.isEmpty();
    }
}
