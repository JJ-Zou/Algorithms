package com.zjj.jvm.memory;

/**
 * jdk8字符常量池在堆中
 * "计算机软件"在调用intern方法时已在堆中，将该字符串放入常量池再返回其引用
 * "java"本身在常量池中，在掉用intern方法时返回的时StringBuilder创建的字符串引用
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
