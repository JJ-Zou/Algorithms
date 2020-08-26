package com.zjj.test1;

import org.junit.Test;

import java.util.Arrays;

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
}
