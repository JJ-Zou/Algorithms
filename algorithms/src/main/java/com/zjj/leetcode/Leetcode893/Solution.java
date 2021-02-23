package com.zjj.leetcode.Leetcode893;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println(numSpecialEquivGroups(new String[]{"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"}));
    }

    public static int numSpecialEquivGroups(String[] A) {
        Set<List<Integer>> set = new HashSet<>();
        for (String s : A) {
            set.add(op(s));
        }
        return set.size();
    }

    private static List<Integer> op(String s) {
        Integer[] ch1 = new Integer[26];
        Integer[] ch2 = new Integer[26];
        int len = s.length();
        for (int i = 0; i < len; i += 2) {
            if (ch1[s.charAt(i) - 'a'] == null) {
                ch1[s.charAt(i) - 'a'] = 0;
            }
            ch1[s.charAt(i) - 'a']++;
        }
        for (int i = 1; i < len; i += 2) {
            if (ch2[s.charAt(i) - 'a'] == null) {
                ch2[s.charAt(i) - 'a'] = 0;
            }
            ch2[s.charAt(i) - 'a']++;
        }
        List<Integer> res = new ArrayList<>(Arrays.asList(ch1));
        res.addAll(Arrays.asList(ch2));
        return res;
    }
}
