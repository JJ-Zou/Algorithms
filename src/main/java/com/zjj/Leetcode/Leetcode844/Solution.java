package com.zjj.Leetcode.Leetcode844;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(operate("y#fo##f"));
        System.out.println(operate("y#fo##f").length());
    }

    public boolean backspaceCompare(String S, String T) {
        return S.equals(T) || operate(S).equals(operate(T));
    }
    private static String operate(String str) {
        char[] ch = str.toCharArray();
        int len = ch.length;
        int read = 0;
        int write = -1;
        while (read < len) {
            while (read < len && ch[read] == '#') {
                if (write >= 0) {
                    write--;
                }
                read++;
            }
            if (read == len) {
                break;
            }
            write++;
            ch[write] = ch[read];
            read++;
        }
        return new String(Arrays.copyOf(ch, write + 1));
    }
}
