package com.ZJJ.nowcoder.ali722.ex2;


import java.util.*;

public class Solution {

    public static int subArray(int n, int m, int[] nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        int len = nums.length;
        int left = -1;
        int res = 0;
        for(int i = 0; i < len; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new LinkedList<>());
            }
            LinkedList<Integer> list = map.get(nums[i]);
            list.add(i);
            if(list.size() >= m) {
                if (list.size() > m) {
                    list.pollFirst();
                }
                left = Math.max(left, list.getFirst());
            }
            res += left + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(subArray(5, 2, new int[]{1, 2, 3, 4, 4}));
    }
}
