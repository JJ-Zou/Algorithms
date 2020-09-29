package com.zjj.Leetcode.Leetcode211;

import java.util.LinkedHashMap;

public class WordDictionary extends LinkedHashMap<Integer, Integer> {
    static class TrieNode {
        private final TrieNode[] subNode;
        private boolean isEnd;

        public TrieNode() {
            subNode = new TrieNode[26];
        }

        public boolean getEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean contains(char c) {
            return subNode[c - 'a'] != null;
        }

        public void put(char c, TrieNode node) {
            subNode[c - 'a'] = node;
        }

        public TrieNode get(char c) {
            return subNode[c - 'a'];
        }
    }

    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        char[] ch = word.toCharArray();
        TrieNode cur = root;
        for (char c : ch) {
            if (!cur.contains(c)) {
                cur.put(c, new TrieNode());
            }
            cur = cur.get(c);
        }
        cur.setEnd();
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(TrieNode p, String word) {
        if (p == null) {
            return false;
        }
        char[] ch = word.toCharArray();
        int len = ch.length;
        for (int i = 0; i < len; i++) {
            if (ch[i] == '.') {
                for (int j = 0; j < 26; j++) {
                    if (search(p.get((char) ('a' + j)), word.substring(i + 1))) {
                        return true;
                    }
                }
                return false;
            }
            if (!p.contains(ch[i])) {
                return false;
            }
            p = p.get(ch[i]);
        }
        return p.getEnd();
    }
}
