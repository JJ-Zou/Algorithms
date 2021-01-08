package com.zjj.leetcode.Leetcode399;

import java.util.*;

public class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Double> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            map.put(equations.get(i).get(0) + "/" + equations.get(i).get(1), values[i]);
            map.put(equations.get(i).get(1) + "/" + equations.get(i).get(0), 1 / values[i]);
            if (!map.containsKey(equations.get(i).get(0))) {
                adj.put(equations.get(i).get(0), new ArrayList<>());
            }
            adj.get(equations.get(i).get(0)).add(equations.get(i).get(1));
            if (!map.containsKey(equations.get(i).get(1))) {
                adj.put(equations.get(i).get(1), new ArrayList<>());
            }
            adj.get(equations.get(i).get(1)).add(equations.get(i).get(0));
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            res[i] = calc(queries.get(i).get(0), queries.get(i).get(1), adj, map);
        }
        return res;
    }

    private double calc(String x, String y, Map<String, List<String>> adj, Map<String, Double> map) {
        double res = -1.0;
        if (!adj.containsKey(x) || !adj.containsKey(y)) {
            return res;
        }
        if (Objects.equals(x, y)) {
            return 1.0;
        }
        if (map.containsKey(x + "/" + y)) {
            return map.get(x + "/" + y);
        }
        for (String s : adj.get(x)) {
            res = calc(x, s, adj, map) * calc(s, y, adj, map);
        }
        return res;
    }
}