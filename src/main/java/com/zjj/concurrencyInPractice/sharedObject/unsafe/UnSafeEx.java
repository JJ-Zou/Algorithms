package com.zjj.concurrencyInPractice.sharedObject.unsafe;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

public class UnSafeEx {
    @SneakyThrows
    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        System.out.println(unsafe);
    }
}
