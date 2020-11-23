package com.zjj.test.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Test1108 {
    public static void change(List<Integer> list) {
        list.clear();
        list = new ArrayList<>();
        list.add(2);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        change(list);
        System.out.println(list);
    }

    private static void testIf() {
        int i = 1;
        int j = 1;
        int k = 3;
        if (i == j)
            if (j == k)
                System.out.println("j equals k");
            else
                System.out.println("j don't equal k");
    }
}
