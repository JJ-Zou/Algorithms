package com.zjj.Leetcode.Leetcode307;

public class NumArray {
    private final int[] tree;
    private final int n;
    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[2 * n];
        if (2 * n - n >= 0) System.arraycopy(nums, 0, tree, n, 2 * n - n);
        for(int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int i, int val) {
        i += n;
        tree[i] = val;
        while(i > 0) {
            int left = i;
            int right = i;
            if((i & 1) == 0) {
                right = i + 1;
            } else {
                left = i - 1;
            }
            tree[i / 2] = tree[left] + tree[right];
            i /= 2;
        }
    }

    public int sumRange(int i, int j) {
        i += n;
        j += n;
        int sum = 0;
        while(i <= j) {
            if((i & 1) == 1) {
                sum += tree[i++];
            }
            if((j & 1) == 0) {
                sum += tree[j--];
            }
            i /= 2;
            j /= 2;
        }
        return sum;
    }
}
