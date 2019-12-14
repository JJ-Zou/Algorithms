package com.ZJJ.Leetcode.Leetcode28;

import org.junit.Test;


public class TestStrStr {
    @Test
    public void name() throws Exception {
        StrStr strStr = new StrStr();
        String haystack = "ssssssdjladjsakldjkljdqwblfdfsfdkfewbufiqb;oirhqfeoif";
        String needle = "";
        System.out.println(strStr.strStr(haystack,needle));
        System.out.println(haystack.indexOf(needle));
    }
}
