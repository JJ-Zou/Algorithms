package com.zjj.bugs.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BugComputeIfAbsent {
    private static ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
    private static String s1 = "AaAa";
    private static String s2 = "BBBB";

    public static void main(String[] args) {
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(map.computeIfAbsent(s1, s -> map.computeIfAbsent(s2, k -> k)));
    }

}
