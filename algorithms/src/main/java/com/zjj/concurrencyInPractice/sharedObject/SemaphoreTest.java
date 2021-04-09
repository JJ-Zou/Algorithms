package com.zjj.concurrencyInPractice.sharedObject;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        semaphore.release();
        semaphore.release();
        semaphore.release();
    }
}
