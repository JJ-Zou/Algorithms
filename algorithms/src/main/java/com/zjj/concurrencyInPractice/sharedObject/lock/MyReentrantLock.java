package com.zjj.concurrencyInPractice.sharedObject.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyReentrantLock implements Lock, Serializable {

    private static final long serialVersionUID = -4979165998419132098L;

    private final Sync sync;

    public MyReentrantLock() {
        this.sync = new NonfairSync();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = -2320182954703980737L;

        abstract void lock();
    }

    static class NonfairSync extends Sync {

        private static final long serialVersionUID = -2342988691751908361L;

        @Override
        final void lock() {
            if ((compareAndSetState(0, 1))) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                acquire(1);
            }
        }
    }
}
