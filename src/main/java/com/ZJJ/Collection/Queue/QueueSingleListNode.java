package com.ZJJ.Collection.Queue;

import java.util.Iterator;

/**
 * 使用单链表实现的先入先出队列
 * @param <T>
 */
public class QueueSingleListNode<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
