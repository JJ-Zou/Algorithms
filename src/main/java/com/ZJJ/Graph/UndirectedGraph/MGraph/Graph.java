package com.ZJJ.Graph.UndirectedGraph.MGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 邻接矩阵
 */
public class Graph {
    private int V;
    private int E;
    private int[][] adj;

    //邻接矩阵
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new int[V][V];
    }

    /**
     * 从文件中读出邻接矩阵
     *
     * @param filename
     */
    public Graph(String filename) {
        Scanner scanner = new Scanner(com.ZJJ.Graph.UndirectedGraph.MGraph.Graph.class
                .getClassLoader().getResourceAsStream(filename));
        this.V = scanner.nextInt();
        adj = new int[V][V];
        this.E = scanner.nextInt();
        for (int i = 0; i < E; i++) {
            int edgeFront = scanner.nextInt();
            int edgeBack = scanner.nextInt();
            adj[edgeFront][edgeBack] = 1;
            adj[edgeBack][edgeFront] = 1;
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    /**
     * 得到邻接矩阵
     *
     * @return
     */
    public int[][] getAdj() {
        return adj;
    }

    /**
     * 增加一条边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v][w] = 1;
        adj[w][v] = 1;
        E++;
    }

    /**
     * 两结点是否存在边
     *
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w) {
        return adj[v][w] == 1;
    }

    /**
     * 计算结点的度数
     *
     * @param v
     * @return
     */
    public int degree(int v) {
        return ((List) adj(v)).size();
    }

    /**
     * 和v相邻的所有顶点(邻接矩阵)
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append("个顶点,").append(E).append("条边\n");
        sb.append("   ");
        for (int i = 0; i < V; i++) {
            sb.append(i + 1).append(" ");
        }
        sb.append("\n   -------------\n");
        for (int i = 0; i < V; i++) {
            sb.append(i + 1).append(" |");
            for (int j = 0; j < V; j++) {
                sb.append(adj[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
