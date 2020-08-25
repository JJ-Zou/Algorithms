package com.zz.Graph.UndirectedGraph;

import com.zz.Graph.UndirectedGraph.LGraph.Graph;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    /**
     * @param G 无向图
     * @param s 起点（任意点）
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int getCount() {
        return count;
    }
}
