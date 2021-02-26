package com.zjj.designPattern.singleton;

public class Singleton3 {
    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        return Inner.INSTANCE;
    }

    private static class Inner {
        private final static Singleton3 INSTANCE = new Singleton3();
    }
}
