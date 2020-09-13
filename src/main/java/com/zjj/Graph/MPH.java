package com.zjj.Graph;

import java.util.*;

public class MPH {
    /*
    MPH(G, P, w)
     d <- select-random(P)
     T <- {d}
     while !T.containsAll(P)
           do s <- select-random-except-in-T(P)
              d <- min-distance in dijkstra(G, s, T)
              T <- addAll(path(d, s))

     */
    //P = {"b", "f" ,"g", "j"}
    public static void main(String[] args) {
        Graph graph = new Graph("graph/graph-ex34", false, false);
        int index = 0;
        List<String> p = Arrays.asList("g", "j", "b", "f");
        Collections.shuffle(p);
        String dst = p.get(index++);
        String src;
        Set<String> set = new HashSet<>();
        List<Deque<String>> list = new ArrayList<>();
        Deque<String> deque;
        set.add(dst);
        Dijkstra dij;
        double[] d;
        while (!set.containsAll(p)) {
            src = p.get(index++);
            dij = new Dijkstra(graph, String.valueOf(src));
            dij.dijkstra(graph);
            d = dij.getD();
            for (String s : set) {
                dst = d[s.charAt(0) - 'a'] < d[dst.charAt(0) - 'a'] ? s : dst;
            }
            deque = dij.minPath(graph, src.charAt(0), dst.charAt(0));
            set.addAll(deque);
            list.add(deque);
        }
        System.out.println(set);
        System.out.println(list);
    }

}
