package com.zjj.leetcode.Leetcode399;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(new Solution().calcEquation(equations, values, queries)));
    }

    public double[] calcEquationBFS(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> map = new HashMap<>();
        int vertex = 0;
        for (List<String> equation : equations) {
            if (!map.containsKey(equation.get(0))) {
                map.put(equation.get(0), vertex++);
            }
            if (!map.containsKey(equation.get(1))) {
                map.put(equation.get(1), vertex++);
            }
        }
        List<Pair>[] adj = new List[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new ArrayList<>();
        }
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            int va = map.get(equations.get(i).get(0));
            int vb = map.get(equations.get(i).get(1));
            adj[va].add(new Pair(vb, values[i]));
            adj[vb].add(new Pair(va, 1 / values[i]));
        }
        n = queries.size();
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            List<String> query = queries.get(i);
            if (!map.containsKey(query.get(0)) || !map.containsKey(query.get(1))) {
                res[i] = -1.0;
                continue;
            }
            int va = map.get(query.get(0));
            int vb = map.get(query.get(1));
            if (va == vb) {
                res[i] = 1.0;
                continue;
            }
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addLast(va);
            double[] ratios = new double[vertex];
            Arrays.fill(ratios, -1.0);
            ratios[va] = 1.0;
            while (!deque.isEmpty() && ratios[vb] < 0) {
                int x = deque.pollFirst();
                for (Pair p : adj[x]) {
                    int y = p.getVertex();
                    if (ratios[y] < 0) {
                        ratios[y] = ratios[x] * p.getValue();
                        deque.addLast(y);
                    }
                }
            }
            res[i] = ratios[vb];
        }
        return res;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> map = new HashMap<>();
        int vertex = 0;
        for (List<String> equation : equations) {
            if (!map.containsKey(equation.get(0))) {
                map.put(equation.get(0), vertex++);
            }
            if (!map.containsKey(equation.get(1))) {
                map.put(equation.get(1), vertex++);
            }
        }
        int[] father = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            father[i] = i;
        }
        double[] weight = new double[vertex];
        Arrays.fill(weight, 1.0);
        for (int i = 0; i < equations.size(); i++) {
            int x = map.get(equations.get(i).get(0));
            int y = map.get(equations.get(i).get(1));
            union(father, weight, x, y, values[i]);
        }
        int n = queries.size();
        double[] res = new double[n];
        Arrays.fill(res, -1.0);
        for (int i = 0; i < n; i++) {
            List<String> query = queries.get(i);
            if (!map.containsKey(query.get(0)) || !map.containsKey(query.get(1))) {
                continue;
            }
            int x = map.get(query.get(0));
            int y = map.get(query.get(1));
            int fx = find(father, weight, x);
            int fy = find(father, weight, y);
            if (fx == fy) {
                res[i] = weight[x] / weight[y];
            }
        }
        return res;
    }

    private void union(int[] father, double[] weight, int x, int y, double value) {
        int fx = find(father, weight, x);
        int fy = find(father, weight, y);
        father[fx] = fy;
        weight[fx] = value * weight[y] / weight[x];
    }

    private int find(int[] father, double[] weight, int x) {
        if (father[x] != x) {
            int f = find(father, weight, father[x]);
            weight[x] = weight[x] * weight[father[x]];
            father[x] = f;
        }
        return father[x];
    }

    static class Pair {
        private int vertex;
        private double value;

        public Pair(int vertex, double value) {
            this.vertex = vertex;
            this.value = value;
        }

        public int getVertex() {
            return vertex;
        }

        public double getValue() {
            return value;
        }
    }
}