package com.zjj.lock.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {

    private volatile AtomicBoolean lockStatus;

    private volatile Thread currentThread;

    public SpinLock() {
        this.lockStatus = new AtomicBoolean(false);
        this.currentThread = null;
    }

    public void lock() {
        while (!this.lockStatus.compareAndSet(false, true)) ;
        this.currentThread = Thread.currentThread();
    }

    public void unLock() {
        this.currentThread = null;
        this.lockStatus.set(false);
    }
}
