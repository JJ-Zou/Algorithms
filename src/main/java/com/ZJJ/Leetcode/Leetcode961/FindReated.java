package com.ZJJ.Leetcode.Leetcode961;

import java.util.HashSet;
import java.util.Set;

/**
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 * 遍历一次找出重复值，只需遍历N+1个元素
 */
public class FindReated {
    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<=A.length/2+1;i++){
            if(set.contains(A[i])){
                return A[i];
            }else{
                set.add(A[i]);
            }
        }
         return -1;
    }
}
