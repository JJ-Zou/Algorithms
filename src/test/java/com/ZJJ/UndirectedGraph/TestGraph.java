package com.ZJJ.UndirectedGraph;

import com.ZJJ.Graph.UndirectedGraph.BreadFirstPaths;
import com.ZJJ.Graph.UndirectedGraph.DepthFirstPaths;
import com.ZJJ.Graph.UndirectedGraph.LGraph.Graph;
import org.junit.Test;


public class TestGraph {
    @Test
    public void name() throws Exception {
        Graph graph = new Graph(13);
        graph.addEdge(0,5);
        graph.addEdge(4,3);
        graph.addEdge(0,1);
        graph.addEdge(9,12);
        graph.addEdge(6,4);
        graph.addEdge(5,4);
        graph.addEdge(0,2);
        graph.addEdge(11,12);
        graph.addEdge(9,10);
        graph.addEdge(0,6);
        graph.addEdge(7,8);
        graph.addEdge(9,11);
        graph.addEdge(5,3);
        DepthFirstPaths DFP = new DepthFirstPaths(graph,0);
        for(int i=1;i<13;i++){
            System.out.println("0 to "+i+": "+DFP.pathTo(i));
        }
        System.out.println("\n\n");
        BreadFirstPaths BFP = new BreadFirstPaths(graph,0);
        for(int i=1;i<13;i++){
            System.out.println("0 to "+i+": "+BFP.pathTo(i));
        }
    }

    @Test
    public void testLG() throws Exception {
        Graph graph = new Graph(Graph.class
                .getClassLoader().getResourceAsStream("graph1.txt"));
        System.out.println(graph);
    }
}
