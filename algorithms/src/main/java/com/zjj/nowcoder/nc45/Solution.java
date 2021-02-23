package com.zjj.nowcoder.nc45;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{2, 3});
        list.add(new int[]{3, 4});
        int[][] array = new int[list.size()][];
        list.toArray(array);
        System.out.println(Arrays.deepToString(array));
    }
}
