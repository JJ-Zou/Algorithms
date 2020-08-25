package com.zjj.Leetcode.Leetcode925;

import java.util.Objects;

public class LongPressedName {
    public boolean isLongPressedName(String name, String typed) {
        if (Objects.equals(name, typed)) {
            return true;
        }
        if (name == null || name.length() == 0 || typed == null || typed.length() < name.length()) {
            return false;
        }
        int left = 0;
        int right = 0;
        while (left < name.length()) {
            if (right == typed.length()) {
                return false;
            }
            if (name.charAt(left) == typed.charAt(right)) {
                left++;
                right++;
            } else if (right == 0 || typed.charAt(right) != typed.charAt(right - 1)) {
                return false;
            } else {
                while (typed.charAt(right) == typed.charAt(right - 1)) {
                    right++;
                    if (right == typed.length()) {
                        return false;
                    }
                }
            }
        }
        while (right < typed.length() && typed.charAt(right) == typed.charAt(right - 1)) {
            right++;
        }
        return right == typed.length();
    }
}
