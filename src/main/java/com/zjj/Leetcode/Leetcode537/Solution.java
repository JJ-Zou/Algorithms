package com.zjj.Leetcode.Leetcode537;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.complexNumberMultiply("1+-1i", "1+-1i"));
    }

    public String complexNumberMultiply(String a, String b) {
        int x = 0;
        int y = 0;
        int[] numA = getNum(a);
        int[] numB = getNum(b);
        x = numA[0] * numB[0] - numA[1] * numB[1];
        y = numA[0] * numB[1] + numA[1] * numB[0];
        return x + "+" + y + "i";
    }

    private int[] getNum(String s) {
        String[] str = s.split("\\+");
        int[] res = new int[2];
        res[0] = Integer.parseInt(str[0]);
        res[1] = Integer.parseInt(str[1].substring(0, str[1].length() - 1));
        return res;
    }
}
