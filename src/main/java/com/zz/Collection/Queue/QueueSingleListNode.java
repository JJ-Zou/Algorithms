package com.zz.Collection.Queue;

import com.zz.Node.Node;

import java.util.Iterator;

/**
 * 用带尾结点的单链表定义先入先出队列
 *
 * @param <T>
 */
public class QueueSingleListNode<T> implements Iterable<T> {
    private Node head;
    private Node tail;
    private int size;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public T getHeadValue() {
        return head == null ? null : (T) head.getValue();
    }

    public T getTailValue() {
        return tail == null ? null : (T) tail.getValue();
    }

    public void enqueue(T value) {
        Node node = new Node(value);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            throw new RuntimeException("队列已为空！");
        }
        T val = (T) head.getValue();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return head == null ? null : head.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                T val = (T) cur.getValue();
                cur = cur.getNext();
                return val;
            }
        };
    }
}
