package com.ZJJ.Leetcode.Leetcode9;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x<0  || (x%10 == 0 && x != 0)) {
            return false;
        }
        int reverseNum = 0;
        while(x > reverseNum) {  //若为回文数，位数为偶数时，x==reverseNum结束循环
            reverseNum = reverseNum*10 + x%10;
            x /= 10;
        }
        return x == reverseNum || x == reverseNum/10;
    }
}
