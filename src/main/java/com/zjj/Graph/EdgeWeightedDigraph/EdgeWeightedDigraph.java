package com.zjj.Graph.EdgeWeightedDigraph;

import java.util.TreeSet;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private TreeSet<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = E;
        adj = new TreeSet[V];
        for(int v=0;v<V;v++){
            adj[v] = new TreeSet<>();
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }
    public TreeSet<DirectedEdge> edges(){
        TreeSet<DirectedEdge> set = new TreeSet<>();
        for(int v=0;v<V;v++){
            for(DirectedEdge e:adj[v]){
                set.add(e);
            }
        }
        return set;
    }
}
