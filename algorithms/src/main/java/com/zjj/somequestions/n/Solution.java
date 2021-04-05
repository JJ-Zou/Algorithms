package com.zjj.somequestions.n;

import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().findKth(new int[]{1, 3, 5, 2, 2}, 5, 3));
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        findKth(a, 0, n - 1, K);
        return a[n - K];
    }

    private void findKth(int[] a, int start, int end, int K) {
        int partition = partition(a, start, end);
        if (partition < K) {
            findKth(a, start, partition - 1, K);
        } else if (partition > K) {
            findKth(a, partition + 1, end, K);
        }
    }

    private int partition(int[] a, int start, int end) {
        if (start > end) {
            return a.length - start + 1;
        }
        swap(a, start, start + new Random().nextInt(end - start + 1));
        int pivot = a[start];
        int left = start - 1;
        int right = end + 1;
        int cur = start + 1;
        while (cur < right) {
            if (a[cur] > pivot) {
                swap(a, cur, --right);
            } else if (a[cur] < pivot) {
                swap(a, cur++, ++left);
            } else {
                cur++;
            }
        }
        return a.length - right + 1;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
