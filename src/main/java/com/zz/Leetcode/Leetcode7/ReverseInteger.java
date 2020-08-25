package com.zz.Leetcode.Leetcode7;


/**
 * x从低位到高位的数字，依次作为反转数字的高位
 * Integer.MAX_VALUE=2147483647,MIN_VALUE=-2147483648
 * 所以在取x最高位时，需要判断是否溢出
 */
public class ReverseInteger {
    public int reverse(int x) {
        int reverse = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if ((reverse > Integer.MAX_VALUE / 10 ||
                    (reverse == Integer.MAX_VALUE / 10 && pop > 7) ||
                    (reverse < Integer.MIN_VALUE / 10 ||
                            (reverse == Integer.MIN_VALUE / 10 && pop < -8)))) {
                return 0;
            }
            reverse = reverse * 10 + pop;
        }
        return reverse;
    }
}
