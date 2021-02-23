package com.zjj.concurrencyInPractice.sharedObject.concurrencyEx;

public interface RejectedExecutionHandler {
    void rejectedExecution(Runnable r, MyThreadPoolExecutor executor);
}
