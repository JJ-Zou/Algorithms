package com.zjj.lock.rwlock;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    private volatile boolean write;

    private final Deque<Thread> waitList;

    public ReadWriteLock() {
        this.write = false;
        this.waitList = new ArrayDeque<>();
    }

    public void tryRead() {
        if (write) {
            LockSupport.park();
        }
        write = false;
    }

    public void tryWrite() {
        if (write) {
            LockSupport.park();
        }
        write = true;
    }

}
