package com.ZJJ.Collection.Stack.StackVariable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 使用可变长数组实现的后入先出栈
 * @param <T>
 */
public class StackVariable<T> implements Iterable<T> {

    private T[] values;

    private int size;

    private int capacity;

    public StackVariable(){
        this(16);
    }

    public StackVariable(int capacity) {
        values = (T[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
    }

    private void resize(int newCapacity){
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(values,0,temp,0,size);
        values = temp;
        capacity = newCapacity;
    }

    public void push(T value){
        if(size == capacity){
            resize(2*capacity);
        }
        values[size++] = value;
    }

    public T pop(){
        if(isEmpty()) {
            throw new RuntimeException("栈已空，无法删除元素！");
        }
        if(size > 0 && size == capacity/4){
            resize(capacity/2);
        }
        T value = values[--size];
        values[size] = null;
        return value;
    }

    public T peek(){
        if(isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        return values[size-1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
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
            private int i=0;
            @Override
            public boolean hasNext() {
                return i<size;
            }

            @Override
            public T next() {
                return values[i++];
            }
        };
    }
}
