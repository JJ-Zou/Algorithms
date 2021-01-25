package com.zjj.leetcode.Leetcode385;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Solution {
    public class NestedIntegerImpl implements NestedInteger {
        public NestedIntegerImpl() {
        }

        public NestedIntegerImpl(Integer integer) {
        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public void setInteger(int value) {

        }

        @Override
        public void add(NestedInteger ni) {

        }

        @Override
        public List<NestedInteger> getList() {
            return null;
        }
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) {
            return new NestedIntegerImpl();
        }
        if (s.charAt(0) != '[') {
            return new NestedIntegerImpl(Integer.parseInt(s));
        }
        StringBuilder numStr = new StringBuilder();
        Deque<NestedInteger> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                deque.addLast(new NestedIntegerImpl());
            } else if (c == ',') {
                if (numStr.length() != 0) {
                    deque.peekLast()
                            .add(new NestedIntegerImpl(Integer.parseInt(numStr.toString())));
                    numStr = new StringBuilder();
                }
            } else if (c == ']') {
                if (numStr.length() != 0) {
                    deque.peekLast()
                            .add(new NestedIntegerImpl(Integer.parseInt(numStr.toString())));
                    numStr = new StringBuilder();
                }
                if (deque.size() > 1) {
                    NestedInteger cur = deque.pollLast();
                    deque.peekLast().add(cur);
                }
            } else {
                numStr.append(c);
            }
        }
        return deque.peekLast();
    }
}