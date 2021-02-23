package com.zjj.Graph;

import lombok.SneakyThrows;

import java.util.*;

public class Graph {
    private int numberOfVertex;
    private int numberOfEdge;
    private double[][] matrix;
    private Set<Edge> edges;
    private Map<Integer, Set<Integer>> map;

    public Graph(String pathname) {
        this(pathname, false, true);
    }

    @SneakyThrows
    public Graph(String pathname, boolean isWeight, boolean isDigit) {
        Scanner scanner = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(pathname)));
        this.numberOfVertex = scanner.nextInt();
        this.numberOfEdge = scanner.nextInt();
        matrix = new double[numberOfVertex][numberOfVertex];
        edges = new HashSet<>();
        map = new HashMap<>();
        int from;
        int to;
        double weight;
        for (int i = 0; i < numberOfEdge; i++) {
            if (isDigit) {
                from = scanner.nextInt() - 1;
                to = scanner.nextInt() - 1;
            } else {
                from = scanner.next().charAt(0) - 'a';
                to = scanner.next().charAt(0) - 'a';
            }
            weight = isWeight ? scanner.nextDouble() : 1;
            edges.add(new Edge(from, to, weight));
            if (!map.containsKey(from)) {
                map.put(from, new HashSet<>());
            }
            map.get(from).add(to);
            if (!map.containsKey(to)) {
                map.put(to, new HashSet<>());
            }
            map.get(to).add(from);
            matrix[from][to] = weight;
            matrix[to][from] = weight;
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graph/graph0", true, false);
        System.out.println(Arrays.toString(graph.getDegree()));
    }

    public int getNumberOfVertex() {
        return numberOfVertex;
    }

    public int getNumberOfEdge() {
        return numberOfEdge;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int[] getDegree() {
        int[] degree = new int[numberOfVertex];
        for (int i = 0; i < numberOfVertex; i++) {
            for (int j = 0; j < numberOfVertex; j++) {
                degree[i] += (matrix[i][j] == 0) ? 0 : 1;
            }
        }
        return degree;
    }

    public Map<Integer, Set<Integer>> getMap() {
        return map;
    }

    public Set<Integer> adj(int v) {
        return map.get(v);
    }

    public double weight(int from, int to) {
        return matrix[from][to];
    }

    static class Edge {
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from &&
                    to == edge.to &&
                    Double.compare(edge.weight, weight) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }
}
