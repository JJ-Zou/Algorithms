package com.zjj.Leetcode.Leetcode127;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.ladderLength("hit", "cog",
                new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean flag = false;
        for (String word : wordList) {
            if (endWord.equals(word)) {
                flag = true;
            }
        }
        if (!flag) {
            return 0;
        }
        int len = wordList.size();
        boolean[] visit = new boolean[len];
        Deque<Pair> deque = new ArrayDeque<>();
        deque.addLast(new Pair(beginWord, 1));
        while (!deque.isEmpty()) {
            Pair p = deque.pollFirst();
            String lastWord = p.key;
            int step = p.value;
            for (int i = 0; i < len; i++) {
                if (!visit[i]) {
                    String curWord = wordList.get(i);
                    if (oneCharDiff(lastWord, curWord)) {
                        visit[i] = true;
                        if (endWord.equals(curWord)) {
                            return step + 1;
                        }
                        deque.addLast(new Pair(curWord, step + 1));
                    }
                }
            }
        }
        return 0;
    }

    private boolean oneCharDiff(String s1, String s2) {
        if (s1.equals(s2)) {
            return false;
        }
        int count = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (count == 1) {
                    return false;
                }
                count++;
            }
        }
        return count == 1;
    }

    class Pair {
        String key;
        int value;

        public Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
