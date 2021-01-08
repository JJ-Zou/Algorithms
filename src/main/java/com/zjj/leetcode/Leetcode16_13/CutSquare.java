package com.zjj.leetcode.Leetcode16_13;

public class CutSquare {
    public double[] cutSquares(int[] square1, int[] square2) {
        double centerX1 = square1[0] + square1[2] / 2.0;
        double centerY1 = square1[1] + square1[2] / 2.0;
        double centerX2 = square2[0] + square2[2] / 2.0;
        double centerY2 = square2[1] + square2[2] / 2.0;
        if (centerX1 == centerX2) {
            return new double[]{centerX1,
                    Math.min(centerY1 - square1[2] / 2.0, centerY2 - square2[2] / 2.0),
                    centerX2,
                    Math.max(centerY2 + square2[2] / 2.0, centerY1 + square1[2] / 2.0)};
        }
        double k = (centerY2 - centerY1) / (centerX2 - centerX1);
        double[] point = new double[8];
        if ((k >= 0 && k <= 1) || (k <= 0 && k >= -1)) {
            point[0] = centerX1 - square1[2] / 2.0;
            point[1] = centerY1 - k * square1[2] / 2.0;
            point[2] = centerX1 + square1[2] / 2.0;
            point[3] = centerY1 + k * square1[2] / 2.0;
            point[4] = centerX2 - square2[2] / 2.0;
            point[5] = centerY2 - k * square2[2] / 2.0;
            point[6] = centerX2 + square2[2] / 2.0;
            point[7] = centerY2 + k * square2[2] / 2.0;
        } else {
            point[0] = centerX1 - square1[2] / 2.0 / k;
            point[1] = centerY1 - square1[2] / 2.0;
            point[2] = centerX1 + square1[2] / 2.0 / k;
            point[3] = centerY1 + square1[2] / 2.0;
            point[4] = centerX2 - square2[2] / 2.0 / k;
            point[5] = centerY2 - square2[2] / 2.0;
            point[6] = centerX2 + square2[2] / 2.0 / k;
            point[7] = centerY2 + square2[2] / 2.0;

        }
        //按横坐标插入排序
        for (int i = 2; i < 8; i += 2) {
            double t1 = point[i];
            double t2 = point[i + 1];
            int j = i;
            for (; j > 0 && t1 < point[j - 2]; j -= 2) {
                point[j] = point[j - 2];
                point[j + 1] = point[j - 1];
            }
            point[j] = t1;
            point[j + 1] = t2;
        }
        return new double[]{point[0], point[1], point[6], point[7]};
    }
}
