package com.zjj.leetcode.Leetcode1839;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public int longestBeautifulSubstring(String word) {
        Set<String> statusSet = Arrays.stream(new String[]{"xa", "aa", "ae", "ee", "ei", "ea", "ii", "io", "ia", "oo", "ou", "oa", "uu", "ua"})
                .collect(Collectors.toSet());
        String status = "x";
        int res = 0;
        int len = 0;
        for (char ch : word.toCharArray()) {
            if (statusSet.contains(status + ch)) {
                if (!status.equals("a") && ch == 'a') {
                    len = 1;
                } else {
                    len++;
                }
                status = String.valueOf(ch);
            } else {
                len = 0;
                status = "x";
            }
            if (status.equals("u")) {
                res = Math.max(res, len);
            }
        }
        return res;
    }
}
