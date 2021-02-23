package com.zjj.leetcode.Leetcode393;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().validUtf8(new int[]{240, 162, 138, 147}));
    }

    public boolean validUtf8(int[] data) {
        int len = data.length;
        int cur = 0;
        while (cur < len) {
            int type = unicodeByte(data[cur]);
            if (type <= 0) {
                return false;
            }
            if (type == 2) {
                cur++;
                if (cur >= len || unicodeByte(data[cur]) != 0) {
                    return false;
                }
            } else if (type == 3) {
                for (int i = 0; i < 2; i++) {
                    cur++;
                    if (cur >= len || unicodeByte(data[cur]) != 0) {
                        return false;
                    }
                }
            } else if (type == 4) {
                for (int i = 0; i < 3; i++) {
                    cur++;
                    if (cur >= len || unicodeByte(data[cur]) != 0) {
                        return false;
                    }
                }
            }
            cur++;
        }
        return true;
    }

    private int unicodeByte(int num) {
        if ((num & 0x80) == 0) {
            return 1;
        }
        int mask = num & 0xf8;
        if ((mask >> 6) == 0b10) {
            return 0;
        }
        if ((mask >> 5) == 0b110) {
            return 2;
        }
        if ((mask >> 4) == 0b1110) {
            return 3;
        }
        if ((mask >> 3) == 0b11110) {
            return 4;
        }
        return -1;
    }
}