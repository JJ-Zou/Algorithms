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
        control.put('a', 1);
        control.put('b', 1);
        control.put('c', 1);
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

    @Test
    public void testStringFormat() {
        System.out.println("2019-01-1   5".compareTo("2019-12-31"));
        System.out.println(String.format("%04d-%02d-%02d", 2012, 1, 1));
    }

    @Test
    public void testHashArray() {
        Set<int[]> set = new HashSet<>();
        int[] cur = new int[]{0, 0};
        set.add(cur);
        cur[0]++;
        set.add(new int[]{cur[0], cur[1]});
        cur[0]--;
        set.add(new int[]{cur[0], cur[1]});
        System.out.println(set);
        for (int[] ints : set) {
            System.out.println(Arrays.toString(ints));
        }
    }

    @Test
    public void testTwoDimensionArray() {
        int[] a1 = new int[]{1};
        int[] a2 = new int[]{1, 2};
        int[] a3 = new int[]{1, 2, 3};
        int[] a4 = new int[]{1, 2, 3, 4};
        List<int[]> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        int[][] array = list.toArray(new int[4][]);
        System.out.println(Arrays.deepToString(array));
        int[][] arr = new int[3][];
        arr[0] = new int[] {1,2,3};
        arr[1] = new int[] {2,3};
        arr[2] = new int[] {3};
        System.out.println(arr);
    }

    @Test
    public void bit2String() {
        System.out.println(Integer.toString(1341, 2));
    }
}
