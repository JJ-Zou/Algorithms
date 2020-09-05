package com.zjj.Leetcode.Leetcode0806;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.hanota(new ArrayList<>(Arrays.asList(1, 0)),
                new ArrayList<>(),
                new ArrayList<>());
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, B, C);
    }

    private void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        }
        hanota(n - 1, A, C, B);
        C.add(A.remove(A.size() - 1));
        hanota(n - 1, B, A, C);
    }
}
