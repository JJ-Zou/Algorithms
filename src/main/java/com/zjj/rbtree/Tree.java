package com.zjj.rbtree;


import java.util.Objects;

public class Tree<K extends Comparable<K>, V> {
    private Entry<K, V> root;

    public Tree() {

    }

    public V get(K key) {
        Entry<K, V> p = getEntry(key);
        return p == null ? null : p.value;
    }

    private Entry<K, V> getEntry(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Entry<K, V> cur = root;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (cur != null) {
            int cmp = k.compareTo(cur.key);
            if (cmp < 0) {
                cur = cur.left;
            } else if (cmp > 0) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        Entry<K, V> cur = root;
        if (root == null) {
            root = new Entry<>(key, value, null);
            return null;
        }
        int cmp;
        Entry<K, V> parent;
        do {
            parent = cur;
            cmp = key.compareTo(cur.key);
            if (cmp < 0) {
                cur = cur.left;
            } else if (cmp > 0) {
                cur = cur.right;
            } else {
                return cur.setValue(value);
            }
        } while (cur != null);
        Entry<K, V> newEntry = new Entry<>(key, value, parent);
        if (cmp < 0) {
            parent.left = newEntry;
        } else {
            parent.right = newEntry;
        }
        fixAfterInsertion(newEntry);
        return null;
    }

    final Entry<K, V> getFirstEntry() {
        Entry<K, V> cur = root;
        if (cur != null) {
            while (cur.left != null) {
                cur = cur.left;
            }
        }
        return cur;
    }

    final Entry<K, V> getLastEntry() {
        Entry<K, V> cur = root;
        if (cur != null) {
            while (cur.right != null) {
                cur = cur.right;
            }
        }
        return cur;
    }

    static <K, V> Tree.Entry<K, V> successor(Entry<K, V> t) {
        if (t == null) {
            return null;
        } else if (t.right != null) {
            Entry<K, V> cur = t.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        } else {
            Entry<K, V> parent = t.parent;
            Entry<K, V> cur = t;
            while (parent != null && cur == parent.right) {
                cur = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    static <K, V> Tree.Entry<K, V> predecessor(Entry<K, V> t) {
        if (t == null) {
            return null;
        } else if (t.left != null) {
            Entry<K, V> cur = t.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur;
        } else {
            Entry<K, V> parent = t.parent;
            Entry<K, V> cur = t;
            while (parent != null && cur == parent.left) {
                cur = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    private void rotateLeft(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null) {
                root = r;
            } else if (p.parent.left == p) {
                p.parent.left = r;
            } else {
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> l = p.left;
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            l.parent = p.parent;
            if (p.parent == null) {
                root = l;
            } else if (p.parent.left == p) {
                p.parent.left = l;
            } else {
                p.parent.right = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private void fixAfterInsertion(Entry<K, V> x) {
        x.color = RED;
        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Entry<K, V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<K, V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    public V remove(Object K) {
        Entry<K, V> p = getEntry(K);
        if (p == null) {
            return null;
        }
        V oldValue = p.value;
        deleteEntry(p);
        return oldValue;
    }

    private void deleteEntry(Entry<K, V> p) {
        if (p.left != null && p.right != null) {
            Entry<K, V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        }

        Entry<K, V> replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            replacement.parent = p.parent;
            if (p.parent == null) {
                root = replacement;
            } else if (p == p.parent.left) {
                p.parent.left = replacement;
            } else {
                p.parent.right = replacement;
            }
            p.left = p.right = p.parent = null;
            if (p.color == BLACK) {
                fixAfterDeletion(replacement);
            }
        } else if (p.parent == null) {
            root = null;//只有根节点 p == root
        } else {
            if (p.color == BLACK) {
                fixAfterDeletion(p);//传的是副本
            }
            if (p.parent != null) {
                if (p == p.parent.left) {
                    p.parent.left = null;
                } else if (p == p.parent.right) {
                    p.parent.right = null;
                }
                p.parent = null;
            }
        }
    }

    private void fixAfterDeletion(Entry<K, V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Entry<K, V> sib = rightOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Entry<K, V> sib = leftOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);
    }

    private Entry<K, V> parentOf(Entry<K, V> p) {
        return p == null ? null : p.parent;
    }

    private Entry<K, V> leftOf(Entry<K, V> p) {
        return p == null ? null : p.left;
    }

    private Entry<K, V> rightOf(Entry<K, V> p) {
        return p == null ? null : p.right;
    }

    private boolean colorOf(Entry<K, V> p) {
        return p == null ? BLACK : p.color;
    }

    private void setColor(Entry<K, V> p, boolean color) {
        if (p != null) {
            p.color = color;
        }
    }

    private final static boolean BLACK = true;
    private final static boolean RED = false;

    static final class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;
        boolean color = BLACK;

        public Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) &&
                    Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
