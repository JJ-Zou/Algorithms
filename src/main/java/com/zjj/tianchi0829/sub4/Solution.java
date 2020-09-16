package com.zjj.tianchi0829.sub4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().suffixQuery("abcdcba"));
    }

    public long suffixQuery(String s) {
        // write your code here
        long res = 0L;
        int len = s.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] ch = s.toCharArray();
        for (int i = 0; i < len; i++) {
            res += 1;
            if (!map.containsKey(ch[i])) {
                map.put(ch[i], new ArrayList<>());
            } else {
                for (int index : map.get(ch[i])) {
                    res += suffixLen(ch, index, i);
                }
            }
            map.get(ch[i]).add(i);
        }
        return res;
    }

    private int suffixLen(char[] ch, int from, int to) {
        int left = from;
        int right = to;
        while (left <= to && right >= from && ch[left] == ch[right]) {
            left++;
            right--;
        }
        return left - from;
    }
}
