package com.zjj.jvm.memory;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * -Xmx20M
 */
public class DirectMemoryOOM {
    private static final int _1Mb = 1024 * 1024;

    @SneakyThrows
    public static void main(String[] args) {
        Field unsafeFiled = Unsafe.class.getDeclaredFields()[0];
        unsafeFiled.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeFiled.get(null);
        System.out.println(unsafe);
        while (true) {
            unsafe.allocateMemory(_1Mb);
        }
    }
}
