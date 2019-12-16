package com.ZJJ.UndirectedGraph;

import com.ZJJ.Graph.UndirectedGraph.DepthFirstPaths;
import com.ZJJ.Graph.UndirectedGraph.LGraph.Graph;
import org.junit.Test;

public class TestDFS {
    @Test
    public void test() {
        DepthFirstPaths dfs = new DepthFirstPaths(new Graph(Graph.class.getClassLoader()
                .getResourceAsStream("graph3.txt")), 0);
        System.out.println(dfs.pathTo(6));
    }

    @Test
    public void test1() {
        DepthFirstPaths dfs = new DepthFirstPaths(new Graph(Graph.class.getClassLoader()
                .getResourceAsStream("graph3.txt")));
        System.out.println(dfs.path());
    }
}
