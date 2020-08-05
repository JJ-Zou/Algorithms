package com.zjj.Algorithms4TH.Chapter1_Basic.Union_Find.QuickFind;


public class QuickFind {
    private int[] id;
    private int count;

    public QuickFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) {
                id[i] = pId;
            }
        }
        count--;
    }

    public int find(int p) {
        return id[p];
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
