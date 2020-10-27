package com.zjj.somequestions.string;

/**
 * 1.intern方法
 * 2.字符串直接赋值和new的区别
 */
public class StringPool {
    public static void main(String[] args) {
        String str1 = new StringBuilder("hello ").append("world").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1.equals(str1.intern()));
        System.out.println(str1 == str1.intern());
        //java字符串在System初始化时被加载
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2.equals(str2.intern()));
        System.out.println(str2 == str2.intern());

        String str3 = "ja" + "va";
        System.out.println(str3);
        System.out.println(str3.intern());
        System.out.println(str3.equals(str3.intern()));
        System.out.println(str3 == str3.intern());
    }
}
