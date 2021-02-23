package com.zjj.leetcode.Leetcode520;

/**
 * 要么全大写，要么全小写，要么首字母大写
 * 分为首字母大写和首字母小写两种情况
 */
public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        /**
         * 首字母小写时，只要其余字母有大写则不合法
         * 字符串长度大于1且首字母大写时，若第二个字符大写，只要其余字母有小写则不合法
         * 字符串长度大于1且首字母大写时，若第二个字符小写，只要其余字母有大写则不合法
         */
        if (word.charAt(0) > 'Z') {
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) < 'a') {
                    return false;
                }
            }
        } else if (word.length() > 1 && word.charAt(1) > 'Z') {
            for (int i = 2; i < word.length(); i++) {
                if (word.charAt(i) < 'a') {
                    return false;
                }
            }
        } else if (word.length() > 1 && word.charAt(1) < 'a') {
            for (int i = 2; i < word.length(); i++) {
                if (word.charAt(i) > 'Z') {
                    return false;
                }
            }
        }
        return true;
    }
}
