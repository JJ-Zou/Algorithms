package com.zjj.test.leetcode.list;

import java.util.*;

public class TestIterator {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        int[] arr1 = {99, 90, 9, 932939};
        String[] arr = {"99", "90", "9", "932939"};
        Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        System.out.println(Arrays.toString(arr));
    }
}
