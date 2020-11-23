package com.zjj.concurrencyInPractice.sharedObject.synchronizedEx;

import java.util.concurrent.TimeUnit;

public class ClassMonitor {
    public synchronized static void m1() {
        System.out.println(Thread.currentThread().getName() + " enter to m1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println(Thread.currentThread().getName() + " enter to m2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::m1).start();
        new Thread(ClassMonitor::m2).start();
    }
}
