package com.ZJJ.Node.SingleListNode;

import com.ZJJ.Node.Node;

/**
 * 单链表
 *
 * @param <T>
 */
public class SingleListNode<T> {

    private Node<T> head;
    private int size;

    public SingleListNode() {
    }

    public SingleListNode(Node<T> head) {
        this.head = head;
        size = 1;
    }

    public int size() {
        return size;
    }

    /**
     * 头插
     *
     * @param value
     */
    public void addFirst(T value) {
        addNode(0, value);
    }

    /**
     * 尾插
     *
     * @param value
     */
    public void addLast(T value) {
        addNode(size, value);
    }

    /**
     * 在链表index索引位置增加一个结点
     *
     * @param value
     * @param index
     */
    public void addNode(int index, T value) {
        Node<T> insert = new Node(value);
        if (index <= 0) {
            insert.setNext(head);
            head = insert;
        } else if (index >= size) {
            if (size == 0) {
                addFirst(value);
                return;
            }
            Node<T> cur = findNode(size - 1);
            cur.setNext(insert);
        } else {
            Node<T> pre = findNode(index - 1);
            insert.setNext(pre.getNext());
            pre.setNext(insert);
        }
        size++;
    }

    /**
     * 删除首节点
     *
     * @return
     */
    public T deleteFirst() {
        return deleteNode(0);
    }

    public T deleteLast() {
        return deleteNode(size - 1);
    }

    /**
     * 删除链表index索引位置的结点
     *
     * @param index
     * @return
     */
    public T deleteNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("索引不合法！");
        }
        if (index == 0) {
            T val = head.getValue();
            head = head.getNext();
            size--;
            return val;
        } else {
            Node<T> pre = findNode(index - 1);
            T value = pre.getNext().getValue();
            pre.setNext(pre.getNext().getNext());
            size--;
            return value;
        }
    }

    /**
     * 将链表index索引位置的值修改为value
     *
     * @param value
     * @param index
     * @return
     */
    public void updateNode(int index, T value) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("索引不合法！");
        }
        Node<T> cur = findNode(index);
        cur.setValue(value);
    }

    /**
     * 查找链表index索引位置的结点
     *
     * @param index
     * @return
     */
    public Node<T> findNode(int index) {
        if (size == 0) {
            return null;
        }
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("索引不合法！");
        }
        Node<T> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur;
    }

    @Override
    public String toString() {
        return head == null ? null : head.toString();
    }
}
