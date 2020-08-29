package com.zjj.test1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ex2 {
    @Test
    public void testMod() {
        System.out.println((-1 + 4) % -4);
        System.out.println(4 % 4);
        System.out.println(5 % 4);
        System.out.println(6 % 4);
    }

    @Test
    public void testArray() {
        int[][] obstacles = new int[][]{{1, 2}, {3, 4}};
        for (int[] obstacle : obstacles) {
            System.out.println(Arrays.toString(obstacle));
        }
    }

    @Test
    public void testSplit() {
        String s = "a ";
        System.out.println(Arrays.toString(s.split(" ")));
    }

    @Test
    public void testArrayEq() {
        int[] arr1 = new int[]{1, 2};
        int[] arr2 = new int[]{1, 2};
        System.out.println(arr1 == arr2);
        System.out.println(arr1.equals(arr2));
        System.out.println(Arrays.equals(arr1, arr2));
    }

    @Test
    public void testListEq() {
        Integer[] arr1 = new Integer[]{1, 2};
        Integer[] arr2 = new Integer[]{1, 2};
        List<Integer> list1 = Arrays.asList(arr1);
        List<Integer> list2 = Arrays.asList(arr2);
        System.out.println(list1 == list2);
        System.out.println(list1.equals(list2));

    }

    @Test
    public void testString2Int() {
        System.out.println(Integer.parseInt("101", 2));
    }
}
