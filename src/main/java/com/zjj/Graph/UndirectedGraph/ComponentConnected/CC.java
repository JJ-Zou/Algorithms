package com.zjj.Graph.UndirectedGraph.ComponentConnected;

import com.zjj.Graph.UndirectedGraph.LGraph.Graph;

import java.util.ArrayList;
import java.util.List;


/**
 * 获取图的连通分量
 */
public class CC {
    private Graph G;
    private int[] markedCount;      //用数组在标记访问的同时记录联通分量
    private int count;              //连通分量个数

    public CC(Graph G) {
        this.G = G;
        markedCount = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            markedCount[i] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (markedCount[v] == -1) {
                dfs(v);
                count++;
            }
        }
    }

    private void dfs(int v) {
        markedCount[v] = count;
        for (int w : G.adj(v)) {
            if (markedCount[w] == -1) {
                dfs(w);
            }
        }
    }

    /**
     * 连通分量个数
     *
     * @return
     */
    public int getCCCount() {
        return count;
    }

    /**
     * 获取图的连通分量集
     *
     * @return
     */
    public Iterable<Integer>[] getCC() {
        List<Integer>[] lists = new ArrayList[count];
        for (int i = 0; i < count; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.V(); v++) {
            lists[markedCount[v]].add(v);
        }
        return lists;
    }

    /**
     * 两点是否连通
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w) {
        return markedCount[v] == markedCount[w];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer>[] lists = (ArrayList<Integer>[]) getCC();
        for (int i = 0; i < lists.length; i++) {
            sb.append(i).append(" : ");
            for (int j = 0; j < lists[i].size(); j++) {
                sb.append(lists[i].get(j) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
