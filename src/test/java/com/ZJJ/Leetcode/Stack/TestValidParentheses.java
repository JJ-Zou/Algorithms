package com.ZJJ.Leetcode.Stack;

import com.ZJJ.Leetcode.Stack.Leetcode20.ValidParentheses;
import org.junit.Test;

public class TestValidParentheses {
    @Test
    public void testValidParentheses() throws Exception {
        ValidParentheses validParentheses = new ValidParentheses();
        String string = "([)]";
        System.out.println(validParentheses.isValid(string));
    }
}
