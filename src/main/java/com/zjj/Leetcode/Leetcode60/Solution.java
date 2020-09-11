package com.zjj.Leetcode.Leetcode60;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getPermutation2(3,
                2));
    }

    public String getPermutation2(int n, int k) {
        if(n == 1) {
            return "1";
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = (i + 1) * fact[i - 1];
        }
        StringBuilder sb = new StringBuilder();
        int cur = n - 2;
        int order = 0;
        while (!list.isEmpty()) {
            order =  k / fact[(cur < 1 ? 1 : cur)];
            sb.append(list.get(order));
            list.remove(order);
            k %= fact[cur];
            if(cur == 0) {
                sb.append(list.get(0));
                return sb.toString();
            }
            cur--;
        }
        return sb.toString();
    }

    private String res;
    private boolean[] visit;
    private int k;

    public String getPermutation(int n, int k) {
        visit = new boolean[n];
        this.k = k;
        back(n, new StringBuilder(), 1);
        return res;
    }

    private void back(int n, StringBuilder sb, int index) {
        if (sb.length() == n) {
            k--;
            if (k == 0) {
                res = sb.toString();
                return;
            }
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visit[i - 1]) {
                visit[i - 1] = true;
                sb.append(i);
                back(n, sb, i + 1);
                if (k == 0) {
                    return;
                }
                visit[i - 1] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
