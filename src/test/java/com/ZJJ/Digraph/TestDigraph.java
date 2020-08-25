package com.ZJJ.Digraph;

import com.ZJJ.Graph.Digraph.DirectedDFS.DigraphDFS;
import com.ZJJ.Graph.Digraph.LGraph.Digraph;
import org.junit.Test;

public class TestDigraph {
    @Test
    public void testDigraph() {
        Digraph digraph = new Digraph("Digraph1.txt");
        DigraphDFS dfs = new DigraphDFS(digraph);
        System.out.println(digraph);
        System.out.println(dfs.hasPathsTo(11));
    }
}
