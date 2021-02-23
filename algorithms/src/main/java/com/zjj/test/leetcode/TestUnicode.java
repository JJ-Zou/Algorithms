package com.zjj.test.leetcode;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class TestUnicode {
    @SneakyThrows
    public static void main(String[] args) {
        String s = "abc";
        Field value = s.getClass().getDeclaredField("value");
        value.setAccessible(true);
        char[] ch = (char[]) value.get(s);
        System.out.println(ch.length);
        System.out.println(s);
        value.set(s, new char[] {'d', 'e', 'f'});
        System.out.println(s);
    }

    private static void unicode() {
        System.out.println("\u03c0");
        System.out.println(0.0 / 0.0);
    }
}
