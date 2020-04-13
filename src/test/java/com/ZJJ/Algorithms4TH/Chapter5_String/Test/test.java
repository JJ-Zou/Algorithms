package com.ZJJ.Algorithms4TH.Chapter5_String.Test;

import com.ZJJ.Algorithms4TH.Chapter1_Basic.Union_Find.QuickFind.QuickFind;
import com.ZJJ.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnion.QuickUnion;
import com.ZJJ.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnionCompression.QuickUnionCompression;
import com.ZJJ.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnionRankCompression.QuickUnionRankCompression;
import com.ZJJ.Algorithms4TH.Chapter1_Basic.Union_Find.RankQuickUnion.RankQuickUnion;
import com.ZJJ.Algorithms4TH.Chapter1_Basic.Union_Find.WeightQuickUnion.WeightQuickUnion;
import com.ZJJ.Algorithms4TH.Chapter5_String.StringSort.StringSort;
import org.junit.Test;

import java.util.Arrays;

public class test {

    @Test
    public void stringSort() throws Exception {
        StringSort stringSort = new StringSort(5000000);
        System.out.println(stringSort.isSorted());
//        System.out.println(Arrays.toString(stringSort.getArray()));
        long time0 = System.currentTimeMillis();
        stringSort.indexCountSort();
        long time1 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(stringSort.getArray()));
        assert stringSort.isSorted();
        System.out.println("isSorted : ? " + stringSort.isSorted() +" " + (time1 - time0) + " ms");
    }
}
