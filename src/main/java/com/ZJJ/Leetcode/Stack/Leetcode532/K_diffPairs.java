package com.ZJJ.Leetcode.Stack.Leetcode532;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中的K-diff数对
 * k=0时，要考虑重复元素
 * k!=0时，不需要考虑重复元素
 */
public class K_diffPairs {
    public int findPairs(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int numsLen = nums.length;
        int counts = 0;
        if(numsLen < 2 || k < 0){
            return 0;
        } else if(k == 0){
            for(int i=0 ; i<numsLen ; i++){
                if(!map.containsKey(nums[i])){
                    map.put(nums[i],1);
                } else if(map.get(nums[i]) == 1) {
                    map.put(nums[i],2);
                    counts++;
                }
            }
            return counts;
        } else {
            for(int i=0 ; i<numsLen ; i++){
                map.put(nums[i],nums[i]+k);
            }
            for(Integer value:map.values()){
                if(map.containsKey(value)){
                    counts++;
                }
            }
            return counts;
        }
    }
}
