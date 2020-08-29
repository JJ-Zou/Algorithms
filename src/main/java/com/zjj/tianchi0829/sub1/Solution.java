package com.zjj.tianchi0829.sub1;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.treePlanning(new int[]{1, 2, 3, 5, 6}, 2));
        System.out.println(solution.treePlanning(new int[]{100000000}, 2));
        System.out.println(solution.treePlanning(new int[]{1, 3, 6, 7, 8, 9, 10}, 2));
    }
    public int treePlanning(int[] trees, int d) {
        // write your code here.
        int len = trees.length;
        int pre = 0;
        int cur = 1;
        int count = 0;
        while(cur < len) {
            if(trees[cur] - trees[pre] < d) {
                cur++;
                count++;
            } else {
                pre = cur;
                cur = cur + 1;
            }
        }
        return  count;
    }
}
