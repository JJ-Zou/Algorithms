package com.zjj.test1;

import org.junit.Test;

import java.util.*;

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
    public void testStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
    }

    @Test
    public void removeTest() {
        Map<Character, Integer> control = new HashMap<>();
        control.put('a',1);
        control.put('b',1);
        control.put('c',1);
        Iterator<Character> iterator = control.keySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 'a') {
                iterator.remove();
            } else {
                control.put('b', 2);
            }
        }
        System.out.println(control);
    }

    @Test
    public void sortArray() {
        int[][] arr = new int[][]{{1, 1}, {3, 4}, {2, 3}};
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        System.out.println(Arrays.deepToString(arr));
    }

    @Test
    public void testTreeSet() {
        Set<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(3);
        set.add(1);
        System.out.println(set);
    }

    @Test
    public void testPq() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(3);
        queue.add(1);
        System.out.println(queue);
    }
}
