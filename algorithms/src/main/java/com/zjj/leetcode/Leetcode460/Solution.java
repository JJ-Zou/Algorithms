package com.zjj.leetcode.Leetcode460;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution(10);
        solution.put(10, 13);
        solution.put(3, 17);
        solution.put(6, 11);
        solution.put(10, 5);
        solution.put(9, 10);
        solution.get(13);
        solution.put(2, 19);
        solution.get(2);
        solution.get(3);
        solution.put(5, 25);
        solution.get(8);
        solution.put(9, 22);
        solution.get(6);
        solution.put(10, 7);
        solution.get(1);
        solution.get(2);
        solution.get(13);
        solution.put(8, 30);
        solution.put(1, 5);
        solution.get(1);
        solution.put(13, 2);
        solution.get(12);
    }

    static class Node {
        int key;
        int value;
        int count;
        Node prev;
        Node next;

        public Node(int key, int value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }

    static class ListNode {

        Node head;
        Node tail;

        public ListNode(Node node) {
            head = node;
            tail = node;
        }

        public void add(Node node) {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        public void remove(Node node) {
            if (node.prev == null) {
                head = node.next;
            } else {
                node.prev.next = node.next;
            }
            if (node.next == null) {
                tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }
        }

        public boolean isEmpty() {
            return head == null;
        }

        public Node poll() {
            Node temp = head;
            if (head.next == null) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev.next = null;
                head.prev = null;
            }
            return temp;
        }
    }

    private Map<Integer, Node> cache;

    private Map<Integer, ListNode> count;

    private int capacity;

    private int least;

    public Solution(int capacity) {
        cache = new HashMap<>();
        count = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        node.count = node.count + 1;
        afterVisit(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!cache.containsKey(key)) {
            if (cache.size() == capacity) {
                Node node = count.get(least).poll();
                if (count.get(least).isEmpty()) {
                    count.remove(least);
                }
                cache.remove(node.key);
            }
            Node node = new Node(key, value, 1);
            cache.put(key, node);
            least = 1;
            if (!count.containsKey(1)) {
                count.put(1, new ListNode(node));
            } else {
                count.get(1).add(node);
            }
            return;
        }
        Node node = cache.get(key);
        node.value = value;
        node.count = node.count + 1;
        afterVisit(node);
    }

    private void afterVisit(Node node) {
        count.get(node.count - 1).remove(node);
        if (count.get(node.count - 1).isEmpty()) {
            count.remove(node.count - 1);
            if(least == node.count - 1) {
                least = node.count;
            }
        }
        if (!count.containsKey(node.count)) {
            count.put(node.count, new ListNode(node));
        } else {
            count.get(node.count).add(node);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
