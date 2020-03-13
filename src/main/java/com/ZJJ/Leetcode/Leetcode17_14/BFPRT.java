package com.ZJJ.Leetcode.Leetcode17_14;

import java.util.Arrays;

/**
 * bfprt算法， 原理很简单， 实现起来比较复杂
 * 在快速排序切分前，获取数组中某一个处于相对中间的数字进行切分
 * 直到切分到使       less + 1 <= k <= more 为止
 */
public class BFPRT {
    public int[] smallestK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        smallestK(arr, 0, arr.length - 1, k);
        return Arrays.copyOf(arr, k);
    }

    public void smallestK(int[] arr, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int less = left - 1;
        int more = right + 1;
        int cur = left;
        int pivot = middle(arr, left, right);
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] == pivot) {
                cur++;
            } else {
                swap(arr, cur, --more);
            }
        }
        if (k < less + 1) {
            smallestK(arr, left, less, k);
        }
        if (k <= more) {
            return;
        }
        smallestK(arr, more, right, k);
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private int middle(int[] arr, int left, int right) {
        int len = right - left + 1;
        int t = 0;
        int cur = 0;
        if (len <= 5) {
            for (int j = left; j <= right; j++) {
                t = arr[j];
                cur = j - 1;
                while (cur >= left && arr[cur] > t) {
                    arr[cur + 1] = arr[cur];
                    cur--;
                }
                arr[cur + 1] = t;
            }
            return arr[left + (len - 1) / 2];
        }
        for (int i = 0; i < len / 5; i++) {
            for (int j = left + 5 * i + 1; j < left + 5 * i + 5 && left + j <= right; j++) {
                t = arr[j];
                cur = j - 1;
                while (cur >= left + 5 * i && arr[cur] > t) {
                    arr[cur + 1] = arr[cur];
                    cur--;
                }
                arr[cur + 1] = t;
            }
        }
        int[] temp = new int[len / 5];
        for (int i = 0; i < len / 5; i++) {
            if (right - left - 5 * i < 5) {
                temp[i] = arr[left + 5 * i + (5 - (right - 5 * i - left) - 1) / 2];
            } else {
                temp[i] = arr[left + 5 * i + 2];
            }
        }
        return middle(temp, 0, len / 5 - 1);
    }
}
