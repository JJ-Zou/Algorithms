package com.zjj.Graph.UndirectedGraph.SymbolGraph;

import com.zjj.Graph.UndirectedGraph.LGraph.Graph;

import java.util.*;

public class SymbolGraph {
    private HashMap<String,Integer> map;
    private String[] keys;
    private Graph G;
    private int V;
    private int E;

    public SymbolGraph(String fileName) {
        map = new HashMap<>();
        Scanner scanner = new Scanner(SymbolGraph.class.getClassLoader()
                .getResourceAsStream(fileName));
        while(scanner.hasNextLine()) {
            String pair = scanner.nextLine();
            String[] pairs = pair.split(",");
            for(int i=0;i<pairs.length;i++) {
                if (!map.containsKey(pairs[i])) {
                    map.put(pairs[i],map.size());
                }
            }
        }
        scanner.close();
        V = map.size();
        keys = new String[V];
        for(String string:map.keySet()){
            keys[map.get(string)] = string;
        }
        G = new Graph(V);
        /**
         * 流只能被读取一次，所以此处重新从文件中创建流
         */
        scanner = new Scanner(SymbolGraph.class.getClassLoader()
                .getResourceAsStream(fileName));
        while(scanner.hasNextLine()) {
            String pair = scanner.nextLine();
            String[] pairs = pair.split(",");
            G.addEdge(map.get(pairs[0]),map.get(pairs[1]));
        }
        E = G.E();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public Iterable<String> getKeys() {
        List<String> list= new LinkedList<>();
        for(String key:keys){
            list.add(key);
        }
        return list;
    }

    public Graph getG() {
        return G;
    }

}
