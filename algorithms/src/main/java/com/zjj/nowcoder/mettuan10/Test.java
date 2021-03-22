package com.zjj.nowcoder.mettuan10;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        int[] ints = Arrays.stream(new String[]{"3", "2"}).map(Integer::valueOf).sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(ints));
        int[] ints1 = Arrays.stream(ints).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(ints1));
        System.out.println(-1 % 6);
    }

    private static void hash(Object key) {

    }
}
