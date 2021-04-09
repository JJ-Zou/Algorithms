package com.zjj.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue<E> {

    public static void main(String[] args) {
        BlockQueue<Integer> queue = new BlockQueue<>(10);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    queue.put(new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    int take = queue.take();
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private final ReentrantLock mainLock;

    private final Condition notEmpty;
    private final Condition notFull;

    private Object[] items;

    private int count;

    private final int capacity;

    private int putIndex;
    private int takeIndex;

    public BlockQueue(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.mainLock = new ReentrantLock();
        this.notEmpty = mainLock.newCondition();
        this.notFull = mainLock.newCondition();
    }


    public void put(E val) throws InterruptedException {
        final ReentrantLock lock = this.mainLock;
        lock.lock();
        try {
            while (count == capacity) {
                notFull.await();
            }
            items[putIndex++] = val;
            if (putIndex == capacity) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        final ReentrantLock lock = this.mainLock;
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            E item = (E) items[takeIndex];
            items[takeIndex++] = null;
            if (takeIndex == capacity) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
