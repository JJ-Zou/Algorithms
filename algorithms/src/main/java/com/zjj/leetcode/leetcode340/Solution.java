package com.zjj.leetcode.leetcode340;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMax("eceba", 2));
    }

    public int findMax(String s, int k) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        int len = s.length();
        int left = 0;
        int right = 0;
        while (right < len) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                if (set.size() == k) {
                    while (set.size() >= k) {
                        set.remove(s.charAt(left++));
                    }
                }
                set.add(c);
            }
            right++;
            res = Math.max(res, right - left);
        }
        return res;
    }
}
