package com.zjj.leetcode.leetcode907;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int sumSubarrayMins(int[] arr) {
        int mod = 1000000007;
        Deque<Integer> pre = new ArrayDeque<>();
        Deque<Integer> post = new ArrayDeque<>();
        int len = arr.length;
        int[] leftArr = new int[len];
        int[] rightArr = new int[len];
        long res = 0;
        for (int i = 0; i < len; i++) {
            while (!pre.isEmpty() && arr[i] < arr[pre.peek()]) {
                pre.pop();
            }
            leftArr[i] = pre.isEmpty() ? (i + 1) : (i - pre.peek());
            pre.push(i);
        }
        for (int i = len - 1; i >= 0; i--) {
            while (!post.isEmpty() && arr[i] <= arr[post.peek()]) {
                post.pop();
            }
            rightArr[i] = post.isEmpty() ? (len - i) : (post.peek() - i);
            post.push(i);
        }
        for (int i = 0; i < len; i++) {
            res = (res + (1L * leftArr[i] * rightArr[i] * arr[i]) % mod) % mod;
        }
        return (int) res;
    }
}