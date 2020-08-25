package com.zz.Algorithms4TH.Chapter1_Basic.Union_Find.QuickUnionRankCompression;

public class QuickUnionRankCompression {
    private int[] id;
    private int[] size;
    private int count;

    public QuickUnionRankCompression(int N) {
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
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public int find(int p) {
        int start = p;
        while (p != id[p]) {
            id[p] = id[id[p]];
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
