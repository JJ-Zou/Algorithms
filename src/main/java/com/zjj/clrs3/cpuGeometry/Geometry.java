package com.zjj.clrs3.cpuGeometry;

/**
 * clrs3 P597
 */
public class Geometry {
    public static void main(String[] args) {
        int[] p1 = {1, 1};
        int[] p2 = {4, 2};
        int[] p3 = {2, 3};
        System.out.println(direction(p1, p2, p3));
    }

    /**
     * @param p1 point1 of line p1p2
     * @param p2 point2 of line p1p2
     * @param p3 point1 of line p3p4
     * @param p4 point2 of line p3p4
     * @return true: segment p1p2 intersects segment p3p4
     */
    public static boolean segmentsIntersect(int[] p1, int[] p2, int[] p3, int[] p4) {
        int d1 = direction(p3, p4, p1);
        int d2 = direction(p3, p4, p2);
        int d3 = direction(p1, p2, p3);
        int d4 = direction(p1, p2, p4);
        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
            return true;
        }
        if (d1 == 0 && (onSegment(p3, p4, p1))) {
            return true;
        }
        if (d2 == 0 && (onSegment(p3, p4, p2))) {
            return true;
        }
        if (d3 == 0 && (onSegment(p1, p2, p3))) {
            return true;
        }
        if (d4 == 0 && (onSegment(p1, p2, p4))) {
            return true;
        }
        return false;
    }

    /**
     * @param p1 point1 of line p1p2
     * @param p2 point2 of line p1p2
     * @param p3 point3
     * @return 0: point3 on the line p1p2;
     * >0: point3 in the clockwise direction of the line p1p2;
     * <0: point3 in the counterclockwise direction of the line p1p2;
     */
    public static int direction(int[] p1, int[] p2, int[] p3) {
        return (p3[0] * p1[0] - p2[1] * p1[1]) - (p2[0] * p1[0] - p3[1] * p1[1]);
    }

    /**
     * @param p1 point1 of line p1p2
     * @param p2 point2 of line p1p2
     * @param p3 point3 on the line p1p2 first
     * @return true: p3 on the segment p1p2
     */
    private static boolean onSegment(int[] p1, int[] p2, int[] p3) {
        int minX = Math.min(p1[0], p2[0]);
        int maxX = Math.max(p1[0], p2[0]);
        int minY = Math.min(p1[1], p2[1]);
        int maxY = Math.max(p1[1], p2[1]);
        return (p3[0] >= minX && p3[0] <= maxX) && (p3[1] >= minY && p3[1] <= maxY);
    }
}
