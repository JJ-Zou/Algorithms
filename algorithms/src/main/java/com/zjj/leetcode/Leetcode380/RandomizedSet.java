package com.zjj.leetcode.Leetcode380;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
public class RandomizedSet {
    private Map<Integer, Integer> indexToVal;
    private Map<Integer, Integer> valToIndex;
    private int size;
    private Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        indexToVal = new HashMap<>();
        valToIndex = new HashMap<>();
        size = 0;
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }
        size++;
        indexToVal.put(size, val);
        valToIndex.put(val, size);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        int targetIndex = valToIndex.get(val);
        int lastVal = indexToVal.get(size);
        indexToVal.put(targetIndex, lastVal);
        indexToVal.remove(size);
        valToIndex.put(lastVal, targetIndex);
        valToIndex.remove(val);
        size--;
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int index = 1 + random.nextInt(size);
        return indexToVal.get(index);
    }
}

