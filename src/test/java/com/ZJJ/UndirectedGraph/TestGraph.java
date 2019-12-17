package com.ZJJ.UndirectedGraph;

import com.ZJJ.Graph.UndirectedGraph.BreadFirstPaths;
import com.ZJJ.Graph.UndirectedGraph.ComponentConnected.CC;
import com.ZJJ.Graph.UndirectedGraph.CycleGraph.Cycle;
import com.ZJJ.Graph.UndirectedGraph.DepthFirstPaths;
import com.ZJJ.Graph.UndirectedGraph.LGraph.Graph;
import com.ZJJ.Graph.UndirectedGraph.SymbolGraph.SymbolGraph;
import com.ZJJ.Graph.UndirectedGraph.TwoColorGraph.TwoColor;
import org.junit.Test;

import java.util.Arrays;


public class TestGraph {
    @Test
    public void name() throws Exception {
        Graph graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(4, 3);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);
        DepthFirstPaths DFP = new DepthFirstPaths(graph, 0);
        for (int i = 1; i < 13; i++) {
            System.out.println("0 to " + i + ": " + DFP.pathTo(i));
        }
        System.out.println("\n\n");
        BreadFirstPaths BFP = new BreadFirstPaths(graph, 0);
        for (int i = 1; i < 13; i++) {
            System.out.println("0 to " + i + ": " + BFP.pathTo(i));
        }
        CC cc = new CC(graph);
        System.out.println(cc.getCCCount());
    }

    @Test
    public void testLG() throws Exception {
        Graph graph = new Graph("graph1.txt");
        System.out.println(graph);
        System.out.println(graph.degree(1));
    }

    @Test
    public void testCycle() {
        Graph graph = new Graph("graph3.txt");
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.hasCycle());
    }

    @Test
    public void test() {
        Graph graph = new Graph("graph3.txt");
        TwoColor twoColor = new TwoColor(graph);
        System.out.println(twoColor.isBipartite());
    }

    @Test
    public void testCC() throws Exception {
        Graph graph = new Graph("graph4.txt");
        CC cc = new CC(graph);
        System.out.println(cc.getCCCount());
        System.out.println(Arrays.toString(cc.getCC()));
        System.out.println(cc);
    }

    @Test
    public void testSymbol() throws Exception {
        SymbolGraph symbolGraph = new SymbolGraph("symbolGraph1.txt");
        System.out.println(symbolGraph.getMap());
        System.out.println(symbolGraph.getKeys());
        System.out.println(symbolGraph);
        System.out.println(symbolGraph.getG());
    }
}
