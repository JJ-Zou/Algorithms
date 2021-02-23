package com.zjj.leetcode.Leetcode166;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.fractionToDecimal(1, Integer.MAX_VALUE));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        if (numerator % denominator == 0) {
            return String.valueOf((long) numerator / (long) denominator);
        }
        StringBuilder sb = new StringBuilder();
        if ((((numerator ^ denominator) >>> 31) & 1) == 1) {
            sb.append("-");
        }
        long n = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);
        if (n > d) {
            sb.append(n / d);
            n %= d;
        } else {
            sb.append(0);
        }
        sb.append(".");
        long remainder = n;
        StringBuilder loop = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        int count = 0;
        while (remainder != 0 && !map.containsKey(remainder)) {
            map.put(remainder, count++);
            remainder *= 10L;
            while (remainder < d) {
                map.put(remainder, count++);
                remainder *= 10L;
                loop.append(0);
            }
            loop.append(remainder / d);
            remainder %= d;
        }
        if (remainder != 0) {
            sb.append(loop.substring(0, map.get(remainder))).append("(")
                    .append(loop.substring(map.get(remainder))).append(")");
        } else {
            sb.append(loop.toString());
        }
        return sb.toString();
    }
    //"0.5"
    //"1.(313102738845)"
    //"0.1(6)"
    //"-6.25"
    //"0.00(000000465661289042462740251655654056577585848337359161441621040707904997124914069194026549138227660723878669455195477065427143370461252966751355553982241280310754777158628319049732085502639731402098131932683780538602845887105337854867197032523144157689601770377165713821223802198558308923834223016478952081795603341592860749337303449725)"
}
