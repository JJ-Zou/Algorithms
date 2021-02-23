package com.zjj.Collection.Bag;

import com.zjj.Node.Node;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    private Node first;
    private Node rear;

    public Node getFirst() {
        return first;
    }

    public Node getRear() {
        return rear;
    }

    public void add(T value) {
        if (first == null) {
            first = new Node(value);
            rear = first;
        } else {
            Node oldrear = rear;
            rear = new Node(value);
            oldrear.setNext(rear);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node cur = first;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                T value = (T) cur.getValue();
                cur = cur.getNext();
                return value;
            }
        };
    }
}
