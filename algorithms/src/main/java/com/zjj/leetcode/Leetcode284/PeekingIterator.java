package com.zjj.leetcode.Leetcode284;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer cur;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        cur = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (cur != null) {
            return cur;
        }
        cur = iterator.next();
        return cur;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (cur != null) {
            Integer t = cur;
            cur = null;
            return t;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return cur != null || iterator.hasNext();
    }
}
