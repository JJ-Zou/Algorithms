package com.zjj.Leetcode.Leetcode67;

/**
 * 二进制求和
 * 从a和b的末尾开始，较短的字符串遍历完后补0
 * 定义进位，
 * sum求字符串当前位置的两数与进位相加 可能值为 0，1，2，3 ,二进制位对其取2的余
 * 进位，sum=0，1 advance=0 sum=2,3 advance=1，故advance = sum/2;
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        StringBuilder res = new StringBuilder();
        int advance = 0;
        int sum = 0;
        while (aIndex >= 0 || bIndex >= 0) {
            sum = advance;
            sum += aIndex >= 0 ? a.charAt(aIndex--) - '0' : 0;
            sum += bIndex >= 0 ? b.charAt(bIndex--) - '0' : 0;
            advance = sum >>> 1;
            res.append(sum % 2);
        }
        if (advance == 1) {
            res.append('1');
        }
        return res.reverse().toString();
    }
}
