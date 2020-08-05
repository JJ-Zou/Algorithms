package com.zjj.Algorithms4TH.Chapter2_Sort.Test;

import com.zjj.Algorithms4TH.Chapter2_Sort.Comparable.ComparableSort;
import com.zjj.Algorithms4TH.Chapter2_Sort.Comparable.PriorityQueue;
import org.junit.Test;
import java.util.Random;

public class test {

    @Test
    public void Sort() throws Exception {
        int N = 5000000;
        Integer[] nums = new Integer[N];
        for (int i = 0; i < N; i++) {
            nums[i] = new Random().nextInt(N);
//            nums[i] = i ;
        }
        System.out.println(ComparableSort.isSorted(nums));
        long t1 = System.currentTimeMillis();
        ComparableSort.heapSort(nums);
        long t2 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(nums));
        assert ComparableSort.isSorted(nums);
        System.out.println("isSorted ? "+ComparableSort.isSorted(nums)+" "+(t2-t1)+" ms");
    }

    @Test
    public void pQ() throws Exception {
//        java.util.PriorityQueue<Integer> queue = new java.util.PriorityQueue<>(2, (o1, o2) -> o2 -o1);
        PriorityQueue<Integer> queue = new PriorityQueue<>(2,(o1, o2) -> o2 -o1);
        System.out.println(queue);
        for (int i = 0; i < 10; i++) {
            queue.add(new Random().nextInt(10));
            System.out.println(queue);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.poll());
            System.out.println(queue);
        }
    }
}
