package com.zjj.leetcode.Leetcode3;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int len = s.length();
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        while (right < len) {
            char c = s.charAt(right);
            if (set.contains(c)) {
                char rm;
                while ((rm = s.charAt(left)) != c) {
                    set.remove(rm);
                    left++;
                }
            }
        }
        return res;
    }
}