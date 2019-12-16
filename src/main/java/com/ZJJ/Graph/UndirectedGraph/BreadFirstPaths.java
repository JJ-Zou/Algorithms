package com.ZJJ.Graph.UndirectedGraph;

import com.ZJJ.Graph.UndirectedGraph.LGraph.Graph;
import com.ZJJ.Node.SingleListNode.SingleListNode;

import java.util.LinkedList;
import java.util.Queue;

public class BreadFirstPaths {
    private final int s;        //起点
    private boolean[] marked;
    private int[] edgeTo;       //从起点到一个顶点的已知路径上的最后一个顶点

    public BreadFirstPaths(Graph G) {
        this.s = 0;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    /**
     * @param G 无向图
     * @param s 起点（任意点）
     */
    public BreadFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    /**
     * 非递归
     * BFS 搜索两点间的最小路径
     * 维护一个队列保存未被检查的结点
     *
     * @param G
     * @param v
     */
    private void bfs(Graph G, int v) {
        marked[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : G.adj(cur)) {
                if (!marked[w]) {
                    edgeTo[w] = cur;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathsTo(int v) {
        return marked[v];
    }

    public SingleListNode<Integer> pathTo(int v) {
        if (!hasPathsTo(v)) {
            return null;
        }
        SingleListNode<Integer> path = new SingleListNode<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.addFirst(x);
        }
        path.addFirst(s);
        return path;
    }

    public SingleListNode<Integer> path() {
        if (!hasPathsTo(marked.length)) {
            return null;
        }
        SingleListNode<Integer> path = new SingleListNode<>();
        for (int x = marked.length; x != s; x = edgeTo[x]) {
            path.addFirst(x);
        }
        path.addFirst(s);
        return path;
    }
}
