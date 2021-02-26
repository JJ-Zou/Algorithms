package com.zjj.jvm.memory;

import java.util.HashSet;
import java.util.Set;

public class StackOverDemo {
    private Set<int[]> set = new HashSet<>();

    public static void main(String[] args) {
        StackOverDemo stackOverDemo = new StackOverDemo();
        stackOverDemo.method0();
    }

    private void method0() {
//        set.add(new int[1024 * 1024]);
        method0();
    }
}
