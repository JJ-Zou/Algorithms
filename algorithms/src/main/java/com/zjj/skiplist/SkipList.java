package com.zjj.skiplist;

import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ThreadLocalRandom;

public class SkipList<K, V> {
    private static final Object BASE_HEADER = new Object();
    final Comparator<? super K> comparator;
    private HeadIndex<K, V> head;

    public SkipList() {
        this.comparator = null;
        initialize();
    }

    @SneakyThrows
    public static void main(String[] args) {

        SkipList<String, Integer> skipList = new SkipList<>();
        int index = 0;
        while (true) {
            int value = ThreadLocalRandom.current().nextInt();
            String key = UUID.randomUUID().toString();
            skipList.put(key, value);
            int v = skipList.get(key);
            if (v != value) {
                throw new RuntimeException();
            }
            int removeValue = skipList.remove(key);
            if (removeValue != value) {
                throw new RuntimeException();
            }
            System.out.println(index++);
        }
    }

    private static void testSkip(SkipList<String, Integer> skipList) {
        for (int i = 0; i < 100000; i++) {
            skipList.put(i + "", i);
        }
        System.out.println("-------------------");
        for (int i = 0; i < 100000; i++) {
            if (i != skipList.get(i + "")) {
                throw new RuntimeException();
            }
        }
        System.out.println("-------------------");
        for (int i = 0; i < 100000; i++) {
            skipList.remove(i + "");
            if (skipList.get(i + "") != null) {
                throw new RuntimeException();
            }
        }
        for (int i = 0; i < 100000; i++) {
            skipList.put(i + "", i);
        }
        System.out.println("-------------------");
        for (int i = 0; i < 100000; i++) {
            if (i != skipList.get(i + "")) {
                throw new RuntimeException();
            }
        }
        System.out.println("-------------------");
        for (int i = 0; i < 100000; i++) {
            skipList.remove(i + "");
            if (skipList.get(i + "") != null) {
                throw new RuntimeException();
            }
        }
        for (int i = 0; i < 100000; i++) {
            if (skipList.get(i + "") != null) {
                System.out.println(skipList.get(i + ""));
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

    public V remove(K key) {
        return doRemove(key, null);
    }

    private V doGet(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Comparator<? super K> cmp = comparator;
        outer:
        for (; ; ) {
            for (Node<K, V> b = findPredecessor(key, cmp), n = b.next; ; ) {
                Object v;
                int c;
                if (n == null) {
                    break outer;
                }
                Node<K, V> f = n.next;
                if ((v = n.value) == null) {
                    n.helpDelete(b, f);
                    break;
                }
                if (b.value == null || v == n) {
                    break;
                }
                if ((c = cpr(cmp, key, n.key)) == 0) {
                    return (V) v;
                }
                if (c < 0) {
                    break outer;
                }
                b = n;
                n = f;
            }
        }
        return null;
    }

    private V doPut(K key, V value, boolean onlyIfAbsent) {
        Node<K, V> z;
        if (key == null) {
            throw new NullPointerException();
        }
        Comparator<? super K> cmp = comparator;
        outer:
        for (; ; ) {
            for (Node<K, V> b = findPredecessor(key, cmp), n = b.next; ; ) {
                if (n != null) {
                    Object v;
                    int c;
                    Node<K, V> f = n.next;
                    if ((v = n.value) == null) {
                        n.helpDelete(b, f);
                        break;
                    }
                    if (b.value == null || n == v) {
                        break;
                    }
                    if ((c = cpr(cmp, key, n.key)) > 0) {
                        b = n;
                        n = f;
                        continue;
                    }
                    if (c == 0) {
                        V vv = (V) v;
                        if (!onlyIfAbsent) {
                            n.value = value;
                        }
                        return vv;
                    }
                }
                z = new Node<>(key, value, n);
                b.next = z;
                break outer;
            }
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

    public V doRemove(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        Comparator<? super K> cmp = comparator;
        outer:
        for (; ; ) {
            for (Node<K, V> b = findPredecessor(key, cmp), n = b.next; ; ) {
                Object v;
                int c;
                if (n == null) {
                    break outer;
                }
                Node<K, V> f = n.next;
                if ((v = n.value) == null) {
                    n.helpDelete(b, f);
                    break;
                }
                if (b.value == null || v == n) {
                    break;
                }
                if ((c = cpr(cmp, key, n.key)) < 0) {
                    break outer;
                }
                if (c > 0) {
                    b = n;
                    n = f;
                    continue;
                }
                if (value != null && !value.equals(v)) {
                    break outer;
                }
                n.value = null;
                n.next = new Node<>(f);
                b.next = f;
                findPredecessor(key, cmp);
                if (head.right == null) {
                    tryReduceLevel();
                }
                return (V) v;
            }
        }
        return null;
    }

    private void tryReduceLevel() {
        HeadIndex<K, V> h = head;
        HeadIndex<K, V> d;
        HeadIndex<K, V> e;
        if (h.level > 3 &&
                (d = (HeadIndex<K, V>) h.down) != null &&
                (e = (HeadIndex<K, V>) d.down) != null &&
                e.right == null &&
                d.right == null &&
                h.right == null) {
            head = d;
        }
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
                    if (n.value == null) {
                        q.unlink(r);
                        r = q.right;
                        continue;
                    }
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

    static final class Node<K, V> {
        final K key;
        Object value;
        Node<K, V> next;

        public Node(K key, Object value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(Node<K, V> next) {
            this.key = null;
            this.value = this;
            this.next = next;
        }

        void helpDelete(Node<K, V> b, Node<K, V> f) {
            if (f == next && this == b.next) {
                if (f == null || f.value != f) {
                    this.next = new Node<>(f);
                } else {
                    b.next = f.next;
                }
            }
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

        final void unlink(Index<K, V> succ) {
            this.right = succ.right;
        }
    }

    static final class HeadIndex<K, V> extends Index<K, V> {
        final int level;

        public HeadIndex(Node<K, V> node, Index<K, V> down, Index<K, V> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }
}
