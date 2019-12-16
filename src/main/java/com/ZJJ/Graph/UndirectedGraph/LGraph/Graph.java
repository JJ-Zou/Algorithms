package com.ZJJ.Graph.UndirectedGraph.LGraph;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 表示无向图的数据结构
 * 邻接表
 */
public class Graph {
    private int V;
    private int E;
    private List<Integer>[] adj;    //邻接表
    /**
     * 创建一个含有V个顶点但不含边的图
     * @param V 顶点
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for (int v=0;v<V;v++){
            adj[v] = new LinkedList<>();
        }
    }
    public Graph(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        V = scanner.nextInt();
        adj = new LinkedList[V];
        for(int v=0;v<V;v++){
            adj[v] = new LinkedList<>();
        }
        E = scanner.nextInt();
        for(int i=0;i<E;i++) {
            int edgeFront = scanner.nextInt();
            int edgeBack = scanner.nextInt();
            adj[edgeFront].add(edgeBack);
            adj[edgeBack].add(edgeFront);
        }
    }
    /**
     * @return 顶点数
     */
    public int V() {
        return V;
    }

    /**
     * @return 边数
     */
    public int E() {
        return E;
    }

    /**
     * 向图中添加一条边 v-w
     * @param v 顶点
     * @param w 顶点
     */
    public void addEdge(int v,int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }



    /**
     * 计算v的度数
     * @param v 顶点
     * @return 度数，依附于它的边的总数
     */
    public int degree(int v) {
//        int degree = 0;
//        for(int w:adj(v)) {
//            degree++;
//        }
//        return degree;
        return adj(v).size();
    }


    /**
     * 计算所有顶点的最大度数
     * @return
     */
    public int maxDegree() {
        int max = 0;
        for(int v=0;v<V;v++) {
            if(degree(v) > max){
                max = degree(v);
            }
        }
        return max;
    }

    /**
     * 计算所有顶点的平均度数
     * @return
     */
    public double avgDegree() {
        return 2*E()/V();
    }


    /**
     * 计算自环的个数
     * @return
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (int v=0;v<V;v++) {
            for (int w:adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count/2;
    }
    /**
     * 和v相邻的所有顶点(邻接表)
     * @param v 顶点
     * @return 顶点数
     */
    public List<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append(V).append("个顶点,").append(E).append("条边\n");
        for (int v=0;v<V;v++){
            sb.append(v).append(": ");
            for(int w:adj(v)){
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
