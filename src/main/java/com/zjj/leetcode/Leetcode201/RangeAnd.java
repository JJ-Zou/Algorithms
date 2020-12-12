package com.zjj.Leetcode.Leetcode201;

public class RangeAnd {
    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        while ((m >>> offset) != (n >>> offset)) {
            offset++;
        }
        return (m >>> offset) << offset;
    }
}
