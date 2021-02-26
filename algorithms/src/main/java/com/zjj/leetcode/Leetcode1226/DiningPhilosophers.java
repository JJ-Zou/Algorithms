package com.zjj.leetcode.Leetcode1226;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private Semaphore semaphore;
    private Lock[] locks;

    public DiningPhilosophers() {
        semaphore = new Semaphore(4);
        locks = new Lock[5];
        Arrays.fill(locks, new ReentrantLock());
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        semaphore.acquire();
        locks[philosopher].lock();
        pickLeftFork.run();
        locks[(philosopher + 1) % 5].lock();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        locks[philosopher].unlock();
        putRightFork.run();
        locks[(philosopher + 1) % 5].unlock();
        semaphore.release();
    }
}