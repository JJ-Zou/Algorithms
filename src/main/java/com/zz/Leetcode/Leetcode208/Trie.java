package com.zz.Leetcode.Leetcode208;

public class Trie {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        int len = word.length();
        TrieNode cur = root;
        for (int i = 0; i < len; i++) {
            if (!cur.contains(word.charAt(i))) {
                cur.put(word.charAt(i), new TrieNode());
            }
            cur = cur.get(word.charAt(i));
        }
        cur.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {
        int len = prefix.length();
        TrieNode cur = root;
        for (int i = 0; i < len; i++) {
            if (cur.contains(prefix.charAt(i))) {
                cur = cur.get(prefix.charAt(i));
            } else {
                return null;
            }
        }
        return cur;
    }

    static class TrieNode {
        private final TrieNode[] subNodes;
        private boolean end;

        public TrieNode() {
            this.subNodes = new TrieNode[26];
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd() {
            this.end = true;
        }

        public boolean contains(char ch) {
            return subNodes[ch - 'a'] != null;
        }

        public void put(char ch, TrieNode node) {
            subNodes[ch - 'a'] = node;
        }

        public TrieNode get(char ch) {
            return subNodes[ch - 'a'];
        }
    }

}
