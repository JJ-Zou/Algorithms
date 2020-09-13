package com.zjj.Graph;

public class Test {
    public static void main(String[] args) {
//        int source = 1;
        String source = "j";
        Graph graph = new Graph("graph/graph-ex34", false, false);
        Dijkstra dij = new Dijkstra(graph, source);
        dij.printMinPath(graph, false);
    }
}
