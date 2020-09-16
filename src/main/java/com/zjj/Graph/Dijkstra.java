package com.zjj.Graph;

import java.util.*;

/**
 * DIJKSTRA(G, w, s)
 * INITIALIZE-SINGLE-SOURCE(G, s)
 * S <- empty
 * Q <- V[G]
 * while Q != empty
 * do u <- EXTRACT-MIN(Q)
 * S <- S ∪ {u}
 * for each vertex v ∈ Adj[u]
 * do RELAX(u, v, w)
 * <p>
 * RELAX(u, v, w)
 * if d[v] > d[u] + w(u, v)
 * then d[v] <- d[u] + w(u, v)
 */
public class Dijkstra {
    private int s;
    private double[] d;
    private Set<Integer> experienced;
    private PriorityQueue<Integer> queue;
    private int[] pathTo;

    public Dijkstra(Graph graph, Object source) {
        if (source instanceof String) {
            s = ((String) source).charAt(0) - 'a';
        } else {
            s = (int) source - 1;
        }
        int v = graph.getNumberOfVertex();
        d = new double[v];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[s] = 0;
        experienced = new HashSet<>();
        queue = new PriorityQueue<>(Comparator.comparingDouble(o -> d[o]));
        queue.add(s);
    }

    public double[] getD() {
        return d;
    }

    public void dijkstra(Graph graph) {
        pathTo = new int[d.length];
        int u;
        while (!queue.isEmpty()) {
            u = queue.poll();
            experienced.add(u);
            for (int v : graph.adj(u)) {
                relax(graph, u, v);
            }
        }
    }

    private void relax(Graph graph, int u, int v) {
        if (!experienced.contains(v) && d[v] > d[u] + graph.weight(u, v)) {
            d[v] = d[u] + graph.weight(u, v);
            pathTo[v] = u;
            queue.remove(v);
            queue.add(v);
        }
    }

    public Deque<String> minPath(Graph graph, char from, char to) {
        Deque<Integer> path = rawPath(graph, from - 'a', to - 'a');
        Deque<String> minPath = new ArrayDeque<>();
        for (int p : path) {
            minPath.offerLast(asciiToString(p + 'a'));
        }
        return minPath;
    }

    public Deque<Integer> minPath(Graph graph, int from, int to) {
        Deque<Integer> path = rawPath(graph, from - 1, to - 1);
        Deque<Integer> minPath = new ArrayDeque<>();
        for (int p : path) {
            minPath.offerLast(p + 1);
        }
        return minPath;
    }

    private Deque<Integer> rawPath(Graph graph, int from, int to) {
        if (pathTo == null) {
            dijkstra(graph);
        }
        Deque<Integer> path = new ArrayDeque<>();
        int last = to;
        while (last != from) {
            path.offerFirst(last);
            last = pathTo[last];
        }
        path.offerFirst(from);
        return path;
    }

    public void printMinPath(Graph graph, boolean isDigit) {
        if (pathTo == null) {
            dijkstra(graph);
        }
        for (int i = 0; i < graph.getNumberOfVertex(); i++) {
            System.out.println("min path from " +
                    (isDigit ? (s + 1) : asciiToString(s + 'a')) + " to " +
                    (isDigit ? (i + 1) : asciiToString(i + 'a')) +
                    " : " + " d = " + d[i] + ", path = " +
                    (isDigit ? minPath(graph, s + 1, i + 1) : minPath(graph, (char) (s + 'a'), (char) (i + 'a'))));
        }
    }


    private String asciiToString(int ascii) {
        return String.valueOf((char) ascii);
    }
}
