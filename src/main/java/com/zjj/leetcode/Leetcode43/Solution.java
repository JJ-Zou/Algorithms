package com.zjj.leetcode.Leetcode43;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.add("1234567898765423", "1234567898765423"));
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len2 = num2.length();
        StringBuilder sb = new StringBuilder();
        int cur2 = len2 - 1;
        String res = "0";
        while (cur2 >= 0) {
            StringBuilder temp = multiply(num1, num2.charAt(cur2));
            for (int i = cur2; i < len2 - 1; i++) {
                temp.append("0");
            }
            res = add(res, temp.toString());
            cur2--;
        }
        return res;
    }

    private String add(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 < len2) {
            return add(num2, num1);
        }
        StringBuilder sb = new StringBuilder();
        int cur1 = len1 - 1;
        int cur2 = len2 - 1;
        int advance = 0;
        while (cur2 >= 0) {
            int sum = (num1.charAt(cur1) - '0' + num2.charAt(cur2) - '0') + advance;
            sb.append(sum % 10);
            advance = sum / 10;
            cur1--;
            cur2--;
        }
        while (cur1 >= 0) {
            int sum = (num1.charAt(cur1) - '0') + advance;
            sb.append(sum % 10);
            advance = sum / 10;
            cur1--;
            cur2--;
        }
        if (advance != 0) {
            sb.append(advance);
        }
        sb.reverse();
        return sb.toString();
    }

    private StringBuilder multiply(String num1, char c) {
        StringBuilder sb = new StringBuilder();
        if (c == '0') {
            sb.append("0");
            return sb;
        }
        if (c == '1') {
            sb.append(num1);
            return sb;
        }
        int advance = 0;
        int len = num1.length();
        int cur = len - 1;
        while (cur >= 0) {
            int num = num1.charAt(cur) - '0';
            int mul = num * (c - '0') + advance;
            sb.append(mul % 10);
            advance = mul / 10;
            cur--;
        }
        if (advance != 0) {
            sb.append(advance);
        }
        sb.reverse();
        return sb;
    }
}
