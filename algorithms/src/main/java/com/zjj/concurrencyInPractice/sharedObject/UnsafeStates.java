package com.zjj.concurrencyInPractice.sharedObject;

/**
 * 不安全的对象发布
 * 数组内可变的数据逸出，可以被任意一个调用者修改，私有的数据实际变为公有
 */
public class UnsafeStates {
    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates() {
        return states;
    }
}
