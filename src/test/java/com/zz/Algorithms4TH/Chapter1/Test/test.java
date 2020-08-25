package com.zz.Algorithms4TH.Chapter1.Test;

import com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.QuickFind.QuickFind;
import com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnion.QuickUnion;
import com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnionCompression.QuickUnionCompression;
import com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnionRankCompression.QuickUnionRankCompression;
import com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.RankQuickUnion.RankQuickUnion;
import com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.WeightQuickUnion.WeightQuickUnion;
import org.junit.Test;

public class test {

    @Test
    public void quickFind() throws Exception {
        int N = 300000;
        QuickFind quickFind = new QuickFind(N);
        for (int i = 0; i < N; i++) {
            int p = (int) (Math.random() * N);
            int q = (int) (Math.random() * N);
            quickFind.union(p, q);
        }
        System.out.println(quickFind.count());
    }

    @Test
    public void quickUnion() throws Exception {
        int N = 100000;
        long time0 = 0;
        long time1 = 0;
        long time2 = 0;
        QuickUnion quickUnion = new QuickUnion(N);
        WeightQuickUnion quickUnion1 = new WeightQuickUnion(N);
        for (int i = 0; i < N; i++) {
            int p = (int) (Math.random() * N);
            int q = (int) (Math.random() * N);
            time0 = System.currentTimeMillis();
            quickUnion.union(p, q);
            time1 += System.currentTimeMillis() - time0;
            quickUnion1.union(p, q);
            time2 += System.currentTimeMillis() - time0;
        }
//        quickUnion.union(4,3);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(3,8);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(6,5);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(9,4);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(2,1);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(5,0);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(7,2);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(6,1);
        System.out.println(quickUnion.count() + ": " + time1);
        System.out.println(quickUnion1.count() + ": " + (time2 - time1));
//        System.out.println(Arrays.toString(quickUnion.getId()));
    }

    @Test
    public void WeightQuickUnion() throws Exception {
        int N = 100000;
        RankQuickUnion quickUnion = new RankQuickUnion(N);
        WeightQuickUnion quickUnion1 = new WeightQuickUnion(N);
        for (int i = 0; i < N; i++) {
            int p = (int) (Math.random() * N);
            int q = (int) (Math.random() * N);
            quickUnion.union(p, q);
            quickUnion1.union(p, q);
        }
//        quickUnion.union(4,3);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(3,8);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(6,5);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(9,4);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(2,1);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(5,0);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(7,2);
//        System.out.println(Arrays.toString(quickUnion.getId()));
//        quickUnion.union(6,1);
        System.out.println(quickUnion.count());
        System.out.println(quickUnion1.count());
//        System.out.println(Arrays.toString(quickUnion.getId()));
    }

    @Test
    public void WeightQuickUnion1() throws Exception {
//        while (true) {
        int N = 100000;
        long time0;
        long time1 = 0;
        long time2 = 0;
        long time3 = 0;
        WeightQuickUnion quickUnion = new WeightQuickUnion(N);
        QuickUnionCompression quickUnion1 = new QuickUnionCompression(N);
        QuickUnionRankCompression quickUnion2 = new QuickUnionRankCompression(N);
        for (int i = 0; i < N; i++) {
            int p = (int) (Math.random() * N);
            int q = (int) (Math.random() * N);
            time0 = System.currentTimeMillis();
            quickUnion.union(p, q);
            time1 += System.currentTimeMillis() - time0;
            quickUnion1.union(p, q);
            time2 += System.currentTimeMillis() - time0;
            quickUnion2.union(p, q);
            time3 += System.currentTimeMillis() - time0;
        }
        System.out.println(quickUnion.count() + ": " + time1);
        System.out.println(quickUnion1.count() + ": " + (time2 - time1));
        System.out.println(quickUnion2.count() + ": " + (time3 - time2));
//            Thread.sleep(1000);
//        }
    }
}
