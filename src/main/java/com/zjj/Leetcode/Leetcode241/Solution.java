package com.zjj.Leetcode.Leetcode241;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.diffWaysToCompute("2*3-4*5"));
    }

    private Map<Integer, List<Integer>> cache;
    private int hash;

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> num = new ArrayList<>();
        List<Character> op = new ArrayList<>();
        cache = new HashMap<>();
        char[] ch = input.toCharArray();
        int len = ch.length;
        int cur = 0;
        while (cur < len) {
            if (!isNum(ch[cur])) {
                op.add(ch[cur]);
                cur++;
            } else {
                int n = ch[cur] - '0';
                cur++;
                while (cur < len && isNum(ch[cur])) {
                    n = n * 10 + ch[cur] - '0';
                    cur++;
                }
                num.add(n);
            }
        }
        if (op.isEmpty()) {
            return num;
        }
        hash = num.size();
        return diffWaysToCompute(num, op, 0, hash - 1);
    }

    private List<Integer> diffWaysToCompute(List<Integer> num, List<Character> op, int left, int right) {
        List<Integer> res = new ArrayList<>();
        if (left == right) {
            res.add(num.get(left));
            return res;
        }
        if (right - left == 1) {
            res.add(op(num.get(left), num.get(right), op.get(left)));
            return res;
        }
        for (int i = left; i < right; i++) {
            int key = left + i * hash;
            List<Integer> leftRes = cache.getOrDefault(key, diffWaysToCompute(num, op, key % hash, key / hash));
            key = i + 1 + right * hash;
            List<Integer> rightRes = cache.getOrDefault(key, diffWaysToCompute(num, op, key % hash, key / hash));
            for (int leftRe : leftRes) {
                for (int rightRe : rightRes) {
                    res.add(op(leftRe, rightRe, op.get(i)));
                }
            }
        }
        return res;
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    private int op(int a, int b, char c) {
        int res = 0;
        switch (c) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
        }
        return res;
    }
}
