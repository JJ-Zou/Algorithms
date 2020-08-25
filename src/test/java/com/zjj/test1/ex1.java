package com.zjj.test1;

public class ex1 {
    public static void main(String[] args) {
        for (int i = -Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            if (t6(t3(i)) != t6(t3(i))) {
                System.out.println("error,  " + i);
            }
        }
        System.out.println("OK");
    }

    /**
     * 0bmn - m = m + n
     *
     * @param i
     * @return
     */
    private static int t1(int i) {
        return (i & 0x55555555) + ((i >>> 1) & 0x55555555);
    }

    private static int t2(int i) {
        return i - ((i >>> 1) & 0x55555555);
    }

    private static int t3(int i) {
        return (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    }

    private static int t5(int i) {
        return (i & 0x0f0f0f0f0) + ((i >>> 4) & 0x0f0f0f0f0);
    }

    private static int t6(int i) {
        return (i + ((i >>> 4)) & 0x0f0f0f0f0);
    }
}
