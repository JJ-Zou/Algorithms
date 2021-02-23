package com.zjj.leetcode.Leetcode299;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getHint("1123", "0111"));
    }

    public String getHint(String secret, String guess) {
        int[] countA = new int[10];
        int[] countB = new int[10];
        int len = secret.length();
        int A = 0;
        int B = 0;
        for (int i = 0; i < len; i++) {
            char a = secret.charAt(i);
            char b = guess.charAt(i);
            if (a == b) {
                A++;
            } else {
                countA[a - '0']++;
                countB[b - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            B += Math.min(countA[i], countB[i]);
        }
        return A + "A" + B + "B";
    }
}
