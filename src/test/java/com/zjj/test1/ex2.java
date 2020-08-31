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
        String s = "a a s d d f";
        System.out.println(Arrays.toString(s.split(" ", 2)));
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
    public void testString() {
        char a = 'a';
        char b = '*';
        System.out.println(Character.isAlphabetic(a));
        System.out.println(Character.isAlphabetic(b));
    }

    @Test
    public void stringSort() {
        String[] strings = new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        Arrays.sort(strings, 0, 3,
                (o1, o2) -> {
                    int cmp = o1.substring(o1.indexOf(" ") + 1).compareTo(o2.substring(o2.indexOf(" ") + 1));
                    if (cmp == 0) {
                        return o1.substring(0, o1.indexOf(" ") + 1).compareTo(o2.substring(0, o2.indexOf(" ") + 1));
                    }
                    return cmp;
                });
        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void testString1() {
        String s = "c";
        s = op(s);
        Queue<String> queue = new PriorityQueue<>((o1, o2) -> {
            int h1 = Integer.parseInt(o1.substring(0, 2));
            int h2 = Integer.parseInt(o2.substring(0, 2));
            int m1 = Integer.parseInt(o1.substring(2));
            int m2 = Integer.parseInt(o2.substring(2));
            return (h2 > h1) ? 1 : (h2 == h1 ? Integer.compare(m2, m1) : -1);
        });
        System.out.println(s);
    }
    private String op(String s) {
        s += "a";
        return s;
    }

    @Test
    public void removeTest() {
        Map<Character, Integer> control = new HashMap<>();
        control.put('a',1);
        control.put('b',1);
        control.put('c',1);
        Iterator<Character> iterator = control.keySet().iterator();
        while(iterator.hasNext()) {
            if(iterator.next() == 'a') {
                iterator.remove();
            } else {
                control.put('b',2);
            }
        }
        System.out.println(control);
    }
}
