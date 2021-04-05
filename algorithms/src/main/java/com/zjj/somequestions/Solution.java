package com.zjj.somequestions;

import java.util.HashMap;
import java.util.Map;


public class Solution {
    static class LRU {
        static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, Node> map;
        private Node head;
        private Node tail;
        private int size;
        private int capacity;

        public LRU(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            if (node.prev == null) {
                return node.value;
            }
            if (node.next == null) {
                tail = node.prev;
                tail.next = null;
                node.prev = null;
                node.next = head;
                head.prev = node;
                head = node;
                return node.value;
            }
            Node p = node.prev;
            p.next = node.next;
            node.next.prev = p;
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
            return node.value;
        }

        public void set(int key, int value) {
            if (!map.containsKey(key)) {
                Node node = new Node(key, value);
                map.put(key, node);
                if (head == null) {
                    tail = node;
                } else {
                    node.next = head;
                    head.prev = node;
                }
                head = node;
                size++;
                if (size > capacity) {
                    map.remove(tail.key);
                    Node temp = tail;
                    tail = tail.prev;
                    temp.prev = null;
                    tail.next = null;
                }
                return;
            }
            Node node = map.get(key);
            node.value = value;
            if (node.prev == null) {
                return;
            }
            if (node.next == null) {
                tail = node.prev;
                tail.next = null;
                node.prev = null;
                node.next = head;
                head.prev = node;
                head = node;
                return;
            }
            Node p = node.prev;
            p.next = node.next;
            node.next.prev = p;
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public static void main(String[] args) {
        // write code here
        LRU lru = new LRU(3);

        lru.set(1, 1);
        lru.set(2, 2);
        lru.set(3, 3);
        lru.set(4, 4);
        lru.get(4);
        lru.get(3);
        lru.get(2);
        lru.get(1);
        lru.set(5, 5);
    }
}
