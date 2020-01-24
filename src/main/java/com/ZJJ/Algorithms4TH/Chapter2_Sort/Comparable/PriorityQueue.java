package com.ZJJ.Algorithms4TH.Chapter2_Sort.Comparable;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] queue;
    private int size = 0;
    private final Comparator<? super Key> comparator;

    public PriorityQueue() {
        this(11,null);
    }

    public PriorityQueue(int initialCapacity) {
        this(initialCapacity,null);
    }

    public PriorityQueue(int initialCapacity,Comparator<? super Key> comparator) {
        queue = (Key[]) new Comparable[initialCapacity];
        this.comparator = comparator;
    }

    public boolean add(Key key) {
        int i = size;
        if (i >= queue.length) {
            grow(i + 1);
        }
        size = i + 1;
        if (i == 0) {
            queue[0] = key;
        } else {
            queue[size - 1] = key;
            swim(size - 1);
        }
        return true;
    }

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        if (newCapacity > Integer.MAX_VALUE - 8) {
            newCapacity = (minCapacity > Integer.MAX_VALUE - 8) ?
                    Integer.MAX_VALUE :
                    (Integer.MAX_VALUE - 8);
        }
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private void swim(int k) {
        while (k > 0 && less((k - 1) >>> 1, k)) {
            swap(k, (k - 1) >>> 1);
            k = ((k - 1) >>> 1);
        }
    }

    public Key poll() {
        Key max = queue[0];
        swap(0, --size);
        queue[size] = null;
        sink(0);
        return max;
    }

    private void sink(int k) {
        while (k < (size >>> 1)) {
            int max = (k << 1) + 1;
            if (max < size - 1 && less(max, max + 1)) {
                max++;
            }
            if (!less(k, max)) {
                break;
            }
            swap(k, max);
            k = max;
        }
    }

    private boolean less(int i, int j) {
        if(comparator == null) {
            return queue[i].compareTo(queue[j]) < 0;
        }else {
            return comparator.compare(queue[i],queue[j]) < 0;
        }
    }

    private void swap(int i, int j) {
        Key t = queue[i];
        queue[i] = queue[j];
        queue[j] = t;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(queue,size));
    }
}
