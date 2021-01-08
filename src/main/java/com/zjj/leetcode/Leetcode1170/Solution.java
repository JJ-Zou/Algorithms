package com.zjj.leetcode.Leetcode1170;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.numSmallerByFrequency(new String[]{"bbb", "cc"},
                new String[]{"a", "aa", "aaa", "aaaa"})));
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int wordsLen = words.length;
        int[] fw = new int[wordsLen];
        for (int i = 0; i < wordsLen; i++) {
            fw[i] = f(words[i]);
        }
        Arrays.sort(fw);
        int queriesLen = queries.length;
        int[] res = new int[queriesLen];
        for (int i = 0; i < queriesLen; i++) {
            int p = wordsLen - 1;
            int fq = f(queries[i]);
            while (p >= 0 && fq < fw[p]) {
                p--;
            }
            res[i] = wordsLen - p - 1;
        }
        return res;
    }

    private int f(String s) {
        int len = s.length();
        int[] hash = new int[26];
        for (char c : s.toCharArray()) {
            hash[c - 'a']++;
        }
        for (int h : hash) {
            if (h > 0) {
                return h;
            }
        }
        return 0;
    }
}
