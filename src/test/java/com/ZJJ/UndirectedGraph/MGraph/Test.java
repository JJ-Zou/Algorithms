package com.ZJJ.UndirectedGraph.MGraph;

import com.ZJJ.Graph.UndirectedGraph.MGraph.Graph;

import java.io.InputStream;
import java.lang.management.GarbageCollectorMXBean;

public class Test {
    @org.junit.Test
    public void test() throws Exception {
        Graph graph = new Graph(Graph.class
                .getClassLoader().getResourceAsStream("graph1.txt"));
        System.out.println(graph);
    }
}