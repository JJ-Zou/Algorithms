package com.zjj.Leetcode.Leetcode341;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    private Deque<NestedInteger> deque;

    public NestedIterator(List<NestedInteger> nestedList) {
        deque = new ArrayDeque<>();
        for (NestedInteger nested : nestedList) {
            deque.addLast(nested);
        }
    }

    @Override
    public Integer next() {
        return deque.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (deque.isEmpty()) {
            return false;
        }
        if (deque.peekFirst().isInteger()) {
            return true;
        }
        while (!deque.isEmpty() && !deque.peekFirst().isInteger()) {
            List<NestedInteger> nestedList = deque.pollFirst().getList();
            int size = nestedList.size();
            for (int i = size - 1; i >= 0; i--) {
                deque.addFirst(nestedList.get(i));
            }
        }
        return !deque.isEmpty();
    }
}
