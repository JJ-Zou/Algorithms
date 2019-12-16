package com.ZJJ.Graph.UndirectedGraph;

import com.ZJJ.Graph.UndirectedGraph.LGraph.Graph;
import com.ZJJ.Node.SingleListNode.SingleListNode;

import java.util.Stack;


public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;       //从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;        //起点

    /**
     *
     * @param G 无向图
     * @param s 起点（任意点）
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
//        dfs(G,s);
        dfsNonRecursive(G,s);
    }

    /**
     * 递归
     * DFS 搜索两点间的路径，不能确定最小路径
     * 得到的路径取决于图的表示方式和递归形式
     * @param G
     * @param v
     */
    private void dfs(Graph G,int v) {
        marked[v] = true;
        for(int w:G.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
        }
    }
    /**
     * 非递归
     * DFS 搜索两点间的路径，不能确定最小路径
     * 得到的路径取决于图的表示方式和递归形式
     * @param G
     * @param v
     */
    private void dfsNonRecursive(Graph G,int v) {
        marked[v] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while(!stack.isEmpty()) {
            int cur = stack.peek();         //取栈顶，访问更深一层
            for (int w : G.adj(cur)) {
                if (!marked[w]) {
                    edgeTo[w] = cur;
                    marked[w] = true;
                    stack.push(w);
                    break;          //只要找到一个未被标记的，就跳出for循环
                }
            }
            if(stack.peek() == cur){    //栈中未变化，则出栈顶元素（栈顶元素没有更深一层）
                stack.pop();
            }
        }
    }

    public boolean hasPathsTo(int v){
        return marked[v];
    }
    public SingleListNode<Integer> pathTo(int v){
        if(!hasPathsTo(v)){
            return null;
        }
        SingleListNode<Integer> path = new SingleListNode<>();
        for(int x=v;x!=s;x=edgeTo[x]){
            path.addFirst(x);
        }
        path.addFirst(s);
        return path;
    }
}
