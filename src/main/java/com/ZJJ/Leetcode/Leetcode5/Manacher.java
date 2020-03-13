package com.ZJJ.Leetcode.Leetcode5;

/**
 * 最长回文子串 马拉车算法
 * 首先解决回文子串长度可能为奇偶的问题： 在每个字符中间及字符串两边加上任意字符（比如'#'） 此时字符串长度必定是奇数
 * 最后得到的最大回文子串长度必定是奇数， 挑选其中的奇数位即可（基0）
 * 维护一个记录每个位置回文半径的数组
 * 记录最右回文的右端maxRight， 记录maxRight对应的回文中心curMid， curMid必须随着maxRight的变化变化
 * 记录当前字串目前已知回文半径upset, 记录最长回文串的中心maxIndex， 记录最长回文串的半径maxR， 两者必须随着upset超过maxR时变化
 * 指针cur每次向右移动一位，移动到maxRight到达最右端为止，此时最大回文子串以确定， 不需要再移动
 * 对每个位置的处理：
 *      考虑cur指针与maxRight的位置关系：
 *              cur > maxRight时， 无可用信息， 暴力寻找
 *              cur <= maxRight时， cur在当前的一个最右回文子串内部，
 *                  并且cur的位置一定在curMid右侧，
 *                  那么cur必存在关于curMId对称的一个点(cur')， maxRight也必存在关于curMid的一个点(maxLeft)（回文串右边界）
 *                              maxLeft ...  ... cur' ... curMid ... cur ... ... maxRight
 *                      此时，存在三种位置关系（用cur'的左边界指代所在位置的最长回文串的左边界）cur'位置已经计算过
 *                      1. cur'的左边界与maxLeft重合       此时可以利用cur'的信息， upset置为cur'的半径， 继续暴力寻找
 *                      2. cur'的左边界在maxLeft左侧       此时，cur的半径就等于maxLeft与cur'的距离（简单证明）
 *                      3. cur'的左边界在maxLeft右侧       此时，cur的半径就等于cur'的半径（简单证明）
 */
public class Manacher {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        char[] ch = new char[2 * len + 1];
        ch[0] = '#';
        for (int i = 0; i < len; i++) {
            ch[2 * (i + 1) - 1] = s.charAt(i);
            ch[2 * (i + 1)] = '#';
        }
        len = 2 * len + 1;
        int[] max = new int[len];
        int maxRight = -1;
        int cur = 0;
        int curMid = 0;
        int upset = 1;
        int maxIndex = -1;
        int maxR = -1;
        while (maxRight < len - 1) {
            if (cur > maxRight || max[curMid - (cur - curMid)] == maxRight - cur + 1) {
                upset = (cur > maxRight) ? 1 : maxRight - cur + 1;
                while (cur - upset >= 0 && cur + upset <= len - 1 && ch[cur - upset] == ch[cur + upset]) {
                    upset++;
                }
                if (upset > maxR) {
                    maxR = upset;
                    maxIndex = cur;
                }
                max[cur] = upset;
                if(maxRight != cur + upset - 1) {
                    curMid = cur;
                    maxRight = cur + upset - 1;
                }
            } else {
                max[cur] = Math.min(maxRight - cur + 1, max[curMid - (cur - curMid)]);
            }
            cur++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxIndex - maxR + 2; i <= maxIndex + maxR - 1; i += 2) {
            sb.append(ch[i]);
        }
        return sb.toString();
    }
}
