package com.zz.Digraph;

import com.zz.Graph.Digraph.DirectedDFS.DigraphDFS;
import com.zz.Graph.Digraph.LGraph.Digraph;
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
