package com.ZJJ.Leetcode;

import com.ZJJ.Leetcode.Leetcode20.ValidParentheses;
import org.junit.Test;

public class TestValidParentheses {
    @Test
    public void testValidParentheses() throws Exception {
        ValidParentheses validParentheses = new ValidParentheses();
        String string = "([)]";
        System.out.println(validParentheses.isValid(string));
    }
}
