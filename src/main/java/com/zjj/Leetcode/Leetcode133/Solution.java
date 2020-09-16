package com.zjj.Leetcode.Leetcode133;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {

    Map<Node, Node> map = new HashMap<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node clone = new Node(node.val, new ArrayList<>());
        Map<Node, Node> cloneMap = new HashMap<>();
        cloneMap.put(node, clone);
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(node);
        while (!deque.isEmpty()) {
            Node cur = deque.pollLast();
            for (Node n : cur.neighbors) {
                if (!cloneMap.containsKey(n)) {
                    cloneMap.put(n, new Node(n.val, new ArrayList<>()));
                    deque.addLast(n);
                }
                cloneMap.get(cur).neighbors.add(cloneMap.get(n));
            }
        }
        return clone;
    }

    public Node cloneGraph1(Node node) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        map.put(node, new Node(node.val, new ArrayList<>()));
        for (Node p : node.neighbors) {
            map.get(node).neighbors.add(cloneGraph(p));
        }
        return map.get(node);
    }
}
