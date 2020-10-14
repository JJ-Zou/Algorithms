package com.zjj.Leetcode.Leetcode306;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAdditiveNumber("2312785412749273901234241325472319054830211"));
    }

    public boolean isAdditiveNumber(String num) {
        return backtrack(num, 0, num.length(), "0", "0", 0);
    }

    private boolean backtrack(String num, int cur, int len, String sum, String pre, int k) {
        if (cur == len) {
            return k > 2;
        }
        for (int i = cur; i < len; i++) {
            String n = num.substring(cur, i + 1);
            if ((n.length() > 1 && n.charAt(0) == '0') || (k > 1 && !n.equals(sum))) {
                continue;
            }
            if (backtrack(num, i + 1, len, add(pre, n), n, k + 1)) {
                return true;
            }
        }
        return false;
    }

    private String add(String a, String b) {
        if (a.length() > b.length()) {
            return add(b, a);
        }
        int lenA = a.length();
        int lenB = b.length();
        StringBuilder sb = new StringBuilder();
        int p1 = lenA - 1;
        int p2 = lenB - 1;
        int advance = 0;
        while (p1 >= 0) {
            int sum = a.charAt(p1--) - '0' + b.charAt(p2--) - '0' + advance;
            sb.append(sum % 10);
            advance = sum / 10;
        }
        while (p2 >= 0) {
            int sum = b.charAt(p2--) - '0' + advance;
            sb.append(sum % 10);
            advance = sum / 10;
        }
        if (advance != 0) {
            sb.append(advance);
        }
        sb.reverse();
        return sb.toString();
    }
}
