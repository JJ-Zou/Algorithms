package com.zz.Leetcode.Leetcode69;

public class SqrtX {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int lo = 0;
        int hi = x;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            if (x / mid == mid) {
                return mid;
            } else if (mid > x / mid) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo * lo > x ? lo - 1 : lo;
    }
}
