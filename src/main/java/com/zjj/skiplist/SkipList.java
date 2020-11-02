package com.zjj.skiplist;

import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ThreadLocalRandom;

public class SkipList<K, V> {
    @SneakyThrows
    public static void main(String[] args) {
        SkipList<Integer, Integer> skipList = new SkipList<>();
        for (int i = 0; i < 1000000; i++) {
            skipList.put(i, i);
        }
        System.out.println("-------------------");
        for (int i = 0; i < 1000000; i++) {
            if (i != skipList.get(i)) {
                throw new RuntimeException();
            }
        }
    }

    private static void compareTest() {
        SkipList<Integer, Integer> skipList = new SkipList<>();
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
        long t1 = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            skipList.put(i, i);
        }
        long t2 = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            map.put(i, i);
        }
        long t3 = System.nanoTime();
        System.out.println(skipList.head.level);
        System.out.println(t2 - t1);
        System.out.println(t3 - t2);
    }

    static final class Node<K, V> {
        final K key;
        Object value;
        Node<K, V> next;

        public Node(K key, Object value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    static class Index<K, V> {
        final Node<K, V> node;
        final Index<K, V> down;
        Index<K, V> right;

        public Index(Node<K, V> node, Index<K, V> down, Index<K, V> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }
    }

    static final class HeadIndex<K, V> extends Index<K, V> {
        final int level;

        public HeadIndex(Node<K, V> node, Index<K, V> down, Index<K, V> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }

    final Comparator<? super K> comparator;
    private HeadIndex<K, V> head;
    private static final Object BASE_HEADER = new Object();

    public SkipList() {
        this.comparator = null;
        initialize();
    }

    private void initialize() {
        head = new HeadIndex<>(new Node<K, V>(null, BASE_HEADER, null),
                null, null, 1);
    }

    public V get(K key) {
        return doGet(key);
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        return doPut(key, value, false);
    }

    private V doGet(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Comparator<? super K> cmp = comparator;
        for (Node<K, V> b = findPredecessor(key, cmp), n = b.next; ; ) {
            if (n == null) {
                break;
            }
            Node<K, V> f = n.next;
            int c;
            if ((c = cpr(cmp, key, n.key)) == 0) {
                return (V) n.value;
            }
            if (c < 0) {
                break;
            }
            n = f;
        }
        return null;
    }

    private V doPut(K key, V value, boolean onlyIfAbsent) {
        Node<K, V> z;
        if (key == null) {
            throw new NullPointerException();
        }
        Comparator<? super K> cmp = comparator;
        for (Node<K, V> b = findPredecessor(key, cmp), n = b.next; ; ) {
            if (n != null) {
                Node<K, V> f = n.next;
                int c = cpr(cmp, key, n.key);
                if (c > 0) {
                    b = n;
                    n = f;
                    continue;
                }
                if (c == 0) {
                    V vv = (V) n.value;
                    if (!onlyIfAbsent) {
                        n.value = value;
                    }
                    return vv;
                }
            }
            z = new Node<>(key, value, n);
            b.next = z;
            break;
        }
        int rnd = ThreadLocalRandom.current().nextInt();
        if ((rnd & 0x80000001) == 0) {
            int level = 1;
            int max;
            while (((rnd >>>= 1) & 1) != 0) {
                level++;
            }
            Index<K, V> idx = null;
            HeadIndex<K, V> h = head;
            if (level <= (max = h.level)) {
                for (int i = 1; i <= level; i++) {
                    idx = new Index<>(z, idx, null);
                }
            } else {
                level = max + 1;
                Index<K, V>[] idxs = (Index<K, V>[]) new Index<?, ?>[level + 1];
                for (int i = 1; i <= level; i++) {
                    idx = new Index<>(z, idx, null);
                    idxs[i] = idx;
                }
                int oldLevel = h.level;
                HeadIndex<K, V> newh = h;
                Node<K, V> oldBase = h.node;
                for (int i = oldLevel + 1; i <= level; i++) {
                    newh = new HeadIndex<>(oldBase, newh, idxs[i], i);
                }
                head = newh;
                h = newh;
                idx = idxs[level = oldLevel];
            }
            for (int insertionLevel = level; ; ) {
                int j = h.level;
                for (Index<K, V> q = h, r = q.right, t = idx; ; ) {
                    if (t == null) {
                        return null;
                    }
                    if (r != null) {
                        Node<K, V> n = r.node;
                        int c = cpr(cmp, key, n.key);
                        if (c > 0) {
                            q = r;
                            r = r.right;
                            continue;
                        }
                    }
                    if (j == insertionLevel) {
                        t.right = r;
                        q.right = t;
                        if (--insertionLevel == 0)
                            return null;
                    }

                    if (--j >= insertionLevel && j < level)
                        t = t.down;
                    q = q.down;
                    r = q.right;
                }
            }
        }
        return null;
    }

    private Node<K, V> findPredecessor(K key, Comparator<? super K> cmp) {
        if (key == null) {
            throw new NullPointerException();
        }
        for (; ; ) {
            for (Index<K, V> q = head, r = q.right, d; ; ) {
                if (r != null) {
                    Node<K, V> n = r.node;
                    K k = n.key;
                    if (cpr(cmp, key, k) > 0) {
                        q = r;
                        r = r.right;
                        continue;
                    }
                }
                if ((d = q.down) == null) {
                    return q.node;
                }
                q = d;
                r = d.right;
            }
        }
    }

    private int cpr(Comparator<? super K> c, K key1, K key2) {
        return (c != null) ? c.compare(key1, key2) : ((Comparable) key1).compareTo(key2);
    }
}

