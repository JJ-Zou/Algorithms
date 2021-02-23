package com.zjj.UndirectedGraph.MGraph;

import com.zjj.Graph.UndirectedGraph.MGraph.Graph;

public class Test {
    @org.junit.Test
    public void test() throws Exception {
        Graph graph = new Graph("graph1.txt");
        System.out.println(graph);
    }
}
