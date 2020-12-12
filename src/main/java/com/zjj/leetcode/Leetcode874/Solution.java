package com.zjj.Leetcode.Leetcode874;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        int[] commands = new int[]{4, -1, 4, -2, 4};
        int[][] obstacles = new int[][]{{2, 4}};
        System.out.println(robotSim(commands, obstacles));
    }

    public static int robotSim(int[] commands, int[][] obstacles) {
        Set<Integer> set = new HashSet<>();
        for (int[] arr : obstacles) {
            set.add((arr[0] + 30000) | ((arr[1] + 30000) << 16));
        }
        int direction = 0;// 0:U,1:R,2:D,3:L
        int len = commands.length;
        int x = 0;
        int y = 0;
        int max = 0;
        for (int command : commands) {
            if (command == -1) {
                direction = (direction + 1) % 4;
            } else if (command == -2) {
                direction = (direction + 3) % 4;
            } else {
                switch (direction) {
                    case 0:
                        for (int j = 0; j < command; j++) {
                            if (set.contains((x + 30000) | ((y + 30001) << 16))) {
                                break;
                            }
                            y++;
                            max = Math.max(max, x * x + y * y);
                        }
                        break;
                    case 1:
                        for (int j = 0; j < command; j++) {
                            if (set.contains((x + 30001) | ((y + 30000) << 16))) {
                                break;
                            }
                            x++;
                            max = Math.max(max, x * x + y * y);
                        }
                        break;
                    case 2:
                        for (int j = 0; j < command; j++) {
                            if (set.contains((x + 30000) | ((y + 29999) << 16))) {
                                break;
                            }
                            y--;
                            max = Math.max(max, x * x + y * y);
                        }
                        break;
                    case 3:
                        for (int j = 0; j < command; j++) {
                            if (set.contains((x + 29999) | ((y + 30000) << 16))) {
                                break;
                            }
                            x--;
                            max = Math.max(max, x * x + y * y);
                        }
                        break;
                }
            }
        }
        return max;
    }
}
