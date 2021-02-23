package com.zjj.Algorithms4TH.Chapter1_Basic.Union_Find.RankQuickUnion;

public class RankQuickUnion {
    private int[] id;
    private int[] size;
    private int count;

    public RankQuickUnion(int N) {
        count = N;
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[p] < size[q]) {
            id[rootP] = rootQ;
        } else if (size[p] > size[q]) {
            id[rootQ] = rootP;
        } else {
            id[rootP] = rootQ;
            size[rootQ]++;
        }
        count--;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public int[] getId() {
        return id;
    }
}
