package com.zjj.leetcode.Leetcode460;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LFUCache {
    static class Node {
        int key;
        int value;
        int f;

        public Node(int key, int value, int f) {
            this.key = key;
            this.value = value;
            this.f = f;
        }
    }

    private Map<Integer, Node> frequency;
    private Map<Integer, LinkedList<Node>> valueInfo;
    private int leastFreq;
    private int capacity;
    private int len;

    public LFUCache(int capacity) {
        frequency = new HashMap<>();
        valueInfo = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!frequency.containsKey(key)) {
            return -1;
        }
        put(key, frequency.get(key).value);
        return frequency.get(key).value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!frequency.containsKey(key)) {
            frequency.put(key, new Node(key, value, 1));
            putToList(1, key, value);
        } else {
            putToList(frequency.get(key).f, key, value);
        }
    }

    private void putToList(int f, int key, int value) {
        if (!valueInfo.containsKey(f)) {
            checkValid();
            valueInfo.put(f, new LinkedList<>());
            valueInfo.get(f).addFirst(frequency.get(key));
            leastFreq = 1;
            len++;
        } else {
            if (valueInfo.get(f).remove(frequency.get(key))) {
                if (leastFreq == f && valueInfo.get(f).size() == 0) {
                    leastFreq = f + 1;
                }
                frequency.get(key).f++;
                frequency.get(key).value = value;
                if (!valueInfo.containsKey(f + 1)) {
                    valueInfo.put(f + 1, new LinkedList<>());
                }
                valueInfo.get(f + 1).addFirst(frequency.get(key));
            } else {
                checkValid();
                valueInfo.get(f).addFirst(frequency.get(key));
                leastFreq = 1;
                len++;
            }
        }
    }

    private void checkValid() {
        if (len == capacity) {
            frequency.remove(valueInfo.get(leastFreq).removeLast().key);
            len--;
        }
    }
}
