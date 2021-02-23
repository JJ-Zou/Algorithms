package com.zjj.leetcode.Leetcode386;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public List<Integer> lexicalOrderStream(int n) {
        return IntStream.rangeClosed(1, n)
                .mapToObj(String::valueOf)
                .sorted()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(res, i, n);
        }
        return res;
    }

    private void dfs(List<Integer> list, int num, int n) {
        if (num > n) {
            return;
        }
        list.add(num);
        for (int i = 0; i < 10; i++) {
            dfs(list, num * 10 + i, n);
        }
    }
}