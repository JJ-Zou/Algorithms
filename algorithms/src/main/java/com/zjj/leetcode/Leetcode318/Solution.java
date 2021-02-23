package com.zjj.leetcode.Leetcode318;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(
                new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }

    public int maxProduct(String[] words) {
        int res = 0;
        int len = words.length;
        int[] mask = new int[len];
        for (int i = 0; i < len; i++) {
            mask[i] = getMask(words[i].toCharArray());
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }

    private int getMask(char[] ch) {
        int mask = 0;
        for (char c : ch) {
            mask |= (1 << (c - 'a'));
        }
        return mask;
    }
}
