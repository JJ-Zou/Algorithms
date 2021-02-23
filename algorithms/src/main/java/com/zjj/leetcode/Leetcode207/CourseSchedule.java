package com.zjj.leetcode.Leetcode207;

import java.util.PriorityQueue;

public class CourseSchedule {
    private int[][] matrix;
    private int numCourses;

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println(new CourseSchedule().canFinish(numCourses, prerequisites));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int len = prerequisites.length;
        if (len == 0) {
            return true;
        }
        this.numCourses = numCourses;
        matrix = new int[numCourses][numCourses];
        for (int i = 0; i < len; i++) {
            matrix[prerequisites[i][1]][prerequisites[i][0]] = 1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> toDegree(o1) - toDegree(o2));
        for (int i = 0; i < numCourses; i++) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (toDegree(vertex) > 0) {
                return queue.size() == 0;
            }
            for (int i = 0; i < numCourses; i++) {
                if (matrix[vertex][i] == 1) {
                    matrix[vertex][i] = 0;
                    queue.remove(i);
                    queue.offer(i);
                }
            }
        }
        return true;
    }

    private int toDegree(int vertex) {
        int degree = 0;
        for (int i = 0; i < numCourses; i++) {
            if (matrix[i][vertex] == 1) {
                degree++;
            }
        }
        return degree;
    }
}
