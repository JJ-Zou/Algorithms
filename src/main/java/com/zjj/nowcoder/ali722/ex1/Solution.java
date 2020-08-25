package com.zjj.nowcoder.ali722.ex1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static List<List<Integer>> permutations(int n) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        back(res, list, new boolean[n], 1, n);
        return res;
    }

    private static void back(List<List<Integer>> res, LinkedList<Integer> list,
                             boolean[] visit, int cur, int left) {
        if (left == 0) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 1; i <= visit.length; i++) {
            if (list.isEmpty() || (!visit[i - 1] && !(cur == i - 1 || cur == i + 1))) {
                visit[i - 1] = true;
                list.addLast(i);
                back(res, list, visit, i, left - 1);
                visit[i - 1] = false;
                list.pollLast();
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permutations = permutations(1);
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
        System.out.println(permutations.size());
    }
}
