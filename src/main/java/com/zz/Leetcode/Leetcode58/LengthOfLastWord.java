package com.zz.Leetcode.Leetcode58;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null) {
            return 0;
        }
        int lastIndex = s.length() - 1;
        int count = 0;
        int index = lastIndex;
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }
        while (index >= 0 && s.charAt(index) != ' ') {
            count++;
            index--;
        }
        return count;
    }
}
