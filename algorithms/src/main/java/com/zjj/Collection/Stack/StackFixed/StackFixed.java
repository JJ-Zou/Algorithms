package com.zjj.Collection.Stack.StackFixed;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 使用固定长数组实现的后入先出栈
 *
 * @param <T>
 */
public class StackFixed<T> implements Iterable<T> {

    private T[] values;

    private int size;

    private int capacity;

    public StackFixed() {
        this(16);
    }

    public StackFixed(int capacity) {
        values = (T[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public void push(T value) {
        if (size == capacity) {
            throw new RuntimeException("栈已满，无法加入元素！");
        }
        values[size++] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，无法删除元素！");
        }
        T value = values[--size];
        values[size] = null;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        return values[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "values=" + Arrays.toString(values) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                return values[i++];
            }
        };
    }
}
