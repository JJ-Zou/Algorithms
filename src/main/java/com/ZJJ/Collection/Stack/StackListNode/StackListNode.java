package com.ZJJ.Collection.Stack.StackListNode;

import com.ZJJ.Node.Node;
import java.util.Iterator;

/**
 * 使用单链表实现的后入先出栈
 * @param <T>
 */
public class StackListNode<T> implements Iterable<T> {
    private Node head;
    private int size;

    public void push(T value){
        Node<T> oldHead = head;
        head = new Node();
        head.setValue(value);
        head.setNext(oldHead);
        size++;
    }

    public T pop(){
        if(isEmpty()) {
            throw new RuntimeException("栈已空，无法删除元素！");
        }
        T value = (T) head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public T peek(){
        return (T) head.getValue();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        if(head == null){
            return null;
        }
        return head.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node cur = head;
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
