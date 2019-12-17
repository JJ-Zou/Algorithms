package com.ZJJ.UndirectedGraph;

import com.ZJJ.Graph.UndirectedGraph.DepthFirstPaths;
import com.ZJJ.Graph.UndirectedGraph.LGraph.Graph;
import com.ZJJ.Graph.UndirectedGraph.LGraph.GraphDFS;
import org.junit.Test;

public class TestDFS {
    @Test
    public void test() {
        DepthFirstPaths dfs = new DepthFirstPaths(new Graph("graph3.txt"), 0);
        System.out.println(dfs.pathTo(6));
    }

    @Test
    public void test1() {
        DepthFirstPaths dfs = new DepthFirstPaths(new Graph("graph3.txt"));
        System.out.println(dfs.path());
    }

    @Test
    public void test2() {
        GraphDFS dfs = new GraphDFS(new Graph("graph3.txt"));
        System.out.println(dfs.pre());
        System.out.println(dfs.post());
    }
}
