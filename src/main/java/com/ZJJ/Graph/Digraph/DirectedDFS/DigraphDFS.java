package com.ZJJ.Graph.Digraph.DirectedDFS;

import com.ZJJ.Graph.Digraph.LGraph.Digraph;

public class DigraphDFS {
    private int s;
    private boolean[] marked;
    private int[] edgeTo;


    public DigraphDFS(Digraph G) {
        s = 0;
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DigraphDFS(Digraph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean hasPathsTo(int v) {
        return marked[v];
    }
}
