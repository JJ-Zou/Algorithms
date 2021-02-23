package com.zjj.Graph.UndirectedGraph.LGraph;

import java.util.ArrayList;
import java.util.List;

public class GraphDFS {
    private Graph G;
    private boolean[] marked;
    private List<Integer> pre = new ArrayList<>();          //前序遍历结果
    private List<Integer> post = new ArrayList<>();         //后序遍历结果

    public GraphDFS(Graph G) {
        this.G = G;
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        pre.add(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }
}
