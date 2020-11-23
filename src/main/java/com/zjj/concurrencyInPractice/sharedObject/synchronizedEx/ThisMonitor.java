package com.zjj.concurrencyInPractice.sharedObject.synchronizedEx;

import java.util.concurrent.TimeUnit;

public class ThisMonitor {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " enter to m1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + " enter to m2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::m1).start();
        new Thread(thisMonitor::m2).start();
        ThisMonitor thisMonitor1 = new ThisMonitor();
        new Thread(thisMonitor1::m1).start();
        new Thread(thisMonitor1::m2).start();
    }
}
