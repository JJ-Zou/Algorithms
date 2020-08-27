package com.zjj.Leetcode.Leetcode937;


import java.util.Arrays;

public class ReorderLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (o1, o2) -> {
            String[] splitO1 = o1.split(" ", 2);
            String[] splitO2 = o2.split(" ", 2);
            boolean digitO1 = Character.isDigit(splitO1[1].charAt(0));
            boolean digitO2 = Character.isDigit(splitO2[1].charAt(0));
            if (!digitO1 && !digitO2) {
                int cmp = splitO1[1].compareTo(splitO2[1]);
                return cmp != 0 ? cmp : splitO1[0].compareTo(splitO2[0]);
            }
            return digitO1 ? (digitO2 ? 0 : 1) : -1;
        });
        return logs;
    }
}
