package com.zjj.Leetcode.Leetcode146;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU--LinkedHashMap
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    //    final int capacity;
//
//    public LRUCache(int capacity) {
//        super(capacity, 0.75f, true);
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        return super.getOrDefault(key, -1);
//    }
//
//    public void put(int key, int value) {
//        super.put(key, value);
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry eldest) {
//        return this.size() > capacity;
//    }
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }
    private Map<Integer, Node> map;
    private int k;
    private Node head;
    private Node tail;

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

    private void linkLast(Node p) {
        Node last = tail;
        tail = p;
        if (last == null) {
            head = p;
        } else {
            p.prev = last;
            last.next = p;
        }
    }


    private void afterRemove(Node r) {
        Node p = r;
        Node prev = p.prev;
        Node next = p.next;
        p.prev = null;
        p.next = null;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.k = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node p = map.get(key);
        moveToLast(p);
        return p.value;
    }
    private void moveToLast(Node p) {
        if(p == tail) {
            return;
        }
        Node prev = p.prev;
        Node next = p.next;
        p.prev = null;
        p.next = null;
        if(prev == null) {
            head = next;
            head.prev = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        tail.next = p;
        p.prev = tail;
        tail = p;
    }
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node temp = map.get(key);
            map.remove(key);
            afterRemove(temp);
            Node p = new Node(key, value);
            map.put(key, p);
            linkLast(p);
            return;
        }
        if (map.size() == k) {
            map.remove(head.key);
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
        }
        Node p = new Node(key, value);
        map.put(key, p);
        linkLast(p);
    }
}
