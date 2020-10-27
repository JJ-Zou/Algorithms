package com.zjj.concurrencyInPractice.sharedObject;

/**
 * 非线程安全的可变整数访问器
 */
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
