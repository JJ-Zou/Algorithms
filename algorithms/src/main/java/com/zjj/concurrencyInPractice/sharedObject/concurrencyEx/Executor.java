package com.zjj.concurrencyInPractice.sharedObject.concurrencyEx;

public interface Executor {

    void execute(Runnable command);
}
