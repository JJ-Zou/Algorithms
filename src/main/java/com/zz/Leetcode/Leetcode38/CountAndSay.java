package com.zz.Leetcode.Leetcode38;

/**
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 递归
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String pre = countAndSay(n - 1);
        StringBuilder stringRes = new StringBuilder();
        int count = 1;
        for (int i = 0; i < pre.length(); i++) {
            if (i == pre.length() - 1 || pre.charAt(i) != pre.charAt(i + 1)) {
                stringRes.append(count).append(pre.charAt(i));
                count = 1;
            } else {
                count++;
            }
        }
        return stringRes.toString();
    }
}
