package com.zjj.Leetcode.Leetcode1385;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findTheDistanceValue(new int[]{-636, -388, -114, 100, -677, -901, 463, 765, 367, -494, -483, 592, -869, 396, 973, -86, 655, -120, 124, -488, 108, -101, 743, 101, -72, -978, -190, 712, -307, -649, -472, -6, -951, 624, 323, 910, -147, 999, 168, -962, -873, 416, -274, 187, -717, 215, -744, -717, -470, 697, -433, -186, 155, -179, -648, 254, -818, -522, -5, -985, -637, 82, -351, 25, 828, 328, 885, -103, 904, 405, 308, 497, -538, -512, -360, 13, 406, -20, 958, -540, 459, -156, -310, 147, 251, 857, 491, 879, 338, -437, 991, 323, -551, -902, 887, -854, 563, 47},
                new int[]{321, 796, -245, -674, 47, 568, 622, 808, -583, -362, -389, 418, -387, 738, 131, -239, -662, 436, 436, 508, -49, 217, -826, 753, -748, 836, 241, 966, 52, 169, -228, -639, -681, -487, -127, -555}, 72));
    }

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int res = 0;
        Arrays.sort(arr2);
        for (int a : arr1) {
            if (distance(arr2, a) > d) {
                res++;
            }
        }
        return res;
    }

    private int distance(int[] arr, int n) {
        int len = arr.length;
        if (n < arr[0] || len == 1) {
            return Math.abs(n - arr[0]);
        }
        if (n > arr[len - 1]) {
            return n - arr[len - 1];
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] == n) {
                return 0;
            } else if (arr[mid] > n && arr[mid - 1] < n) {
                return Math.min(arr[mid] - n, n - arr[mid - 1]);
            } else if (arr[mid] > n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return Math.abs(n - arr[left]);
    }
}
