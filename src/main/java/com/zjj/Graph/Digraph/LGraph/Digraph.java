package com.zjj.Graph.Digraph.LGraph;

import java.util.Scanner;
import java.util.TreeSet;

public class Digraph {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = E;
        adj = new TreeSet[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new TreeSet<>();
        }
    }

    public Digraph(String fileName) {
        Scanner scanner = new Scanner(Digraph.class.getClassLoader()
                .getResourceAsStream(fileName));
        V = scanner.nextInt();
        adj = new TreeSet[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new TreeSet<>();
        }
        E = scanner.nextInt();
        for (int i = 0; i < E; i++) {
            int edgeFront = scanner.nextInt();
            int edgeBack = scanner.nextInt();
            adj[edgeFront].add(edgeBack);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append("个顶点,").append(E).append("条边\n");
        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ");
            for (int w : adj(v)) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
