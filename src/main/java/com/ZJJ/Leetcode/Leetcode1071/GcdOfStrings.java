package com.ZJJ.Leetcode.Leetcode1071;

/**
 * 字符串的最大公因子
 */
public class GcdOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        if (str1.equals(str2)) {
            return str1;
        }
        //长的字符串总在前
        if (str1.length() < str2.length()) {
            return gcdOfStrings(str2, str1);
        }
        //满足该条件说明存在最大公因子
        if ((str1 + str2).equals(str2 + str1)) {
            return gcdOfStrings(str1.substring(str2.length()), str2);
        }
        return "";
    }
}
