package com.zjj.Leetcode.Leetcode859;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        String s = "a";
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        set1.add('s');
        set2.add('s');
        System.out.println(set1 == set2);
        System.out.println(set1.equals(set2));

    }

    public static boolean buddyStrings(String A, String B) {
        int len1 = A.length();
        int len2 = B.length();
        if (len1 != len2) {
            return false;
        }
        Set<Character> set = new HashSet<>();
        int count = 0;
        int diff1 = -1;
        int diff2 = -1;
        char[] ch1 = A.toCharArray();
        char[] ch2 = B.toCharArray();
        for (int i = 0; i < len1; i++) {
            if (ch1[i] != ch2[i]) {
                if (count == 0) {
                    diff1 = i;
                    count = 1;
                } else if (count == 1) {
                    diff2 = i;
                    count = 2;
                } else {
                    return false;
                }
            }
            set.add(ch1[i]);
        }
        if (count == 0) {
            return set.size() != len1;
        }
        return count == 2 && ch1[diff1] == ch2[diff2] && ch1[diff2] == ch2[diff1];
    }
}
