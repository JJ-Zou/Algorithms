package com.zjj.Leetcode.Leetcode146;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU--LinkedHashMap
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return this.size() > capacity;
    }
}
