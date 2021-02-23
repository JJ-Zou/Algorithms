package com.zjj.concurrencyInPractice.sharedObject.call;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
    static class CallDemo implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return "hello world";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallDemo());
        new Thread(futureTask, "子线程1").start();
//        futureTask.run();
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());
    }
}
