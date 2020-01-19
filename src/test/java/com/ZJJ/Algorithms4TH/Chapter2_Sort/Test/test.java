package com.ZJJ.Algorithms4TH.Chapter2_Sort.Test;

import com.ZJJ.Algorithms4TH.Chapter2_Sort.Comparable.ComparableSort;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Random;

public class test {

    @Test
    public void Sort() throws Exception {
        int N = 1500000;
        Integer[] nums = new Integer[N];
        for (int i = 0; i < N; i++) {
            nums[i] = new Random().nextInt(N);
//            nums[i] = i % 100000;
        }
        System.out.println(ComparableSort.isSorted(nums));
        long t1 = System.currentTimeMillis();
        ComparableSort.mergeSort(nums);
        long t2 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(nums));
        System.out.println("isSorted ? "+ComparableSort.isSorted(nums)+" "+(t2-t1)+" ms");
    }
}
