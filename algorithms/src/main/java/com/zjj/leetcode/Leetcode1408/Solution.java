package com.zjj.leetcode.Leetcode1408;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.stringMatching(new String[]
                {"leetcoder", "leetcode", "od", "hamlet", "am"}));
    }

    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        int len = words.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (words[i].contains(words[j])) {
                    set.add(words[j]);
                }
            }
        }
        return new ArrayList<>(set);
    }

}
