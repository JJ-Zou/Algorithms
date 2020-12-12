package com.zjj.Leetcode.Leetcode187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findRepeatedDnaSequences1("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        int len = s.length();
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i <= len - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (!set1.contains(sub)) {
                set1.add(sub);
            } else {
                set2.add(sub);
            }
        }
        return new ArrayList<>(set2);
    }

    public List<String> findRepeatedDnaSequences1(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        int len = s.length();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            switch (s.charAt(i)) {
                case 'A':
                    nums[i] = 0;
                    break;
                case 'C':
                    nums[i] = 1;
                    break;
                case 'G':
                    nums[i] = 2;
                    break;
                case 'T':
                    nums[i] = 3;
                    break;
            }
        }
        Set<Integer> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        int hash = 0;
        int top = (1 << 20);
        for (int i = 0; i < 10; i++) {
            hash = hash * 4 + nums[i];
        }
        set1.add(hash);
        for (int i = 1; i <= len - 10; i++) {
            hash = hash * 4 + nums[i + 9] - top * nums[i - 1];
            if (set1.contains(hash)) {
                set2.add(s.substring(i, i + 10));
            }
            set1.add(hash);
        }
        return new ArrayList<>(set2);
    }
}
