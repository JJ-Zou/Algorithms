package com.zjj.Algorithms4TH.Chapter5_String.StringSort;

import java.util.Random;

public class StringSort {
    private static class Node {
        private String name;
        private int key;

        public Node(String name, int key) {
            this.name = name;
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "['" + name + '\'' + ", " + key + ']';
        }
    }

    private Node[] array;
    private final int INDEX = Byte.MAX_VALUE;

    public StringSort(int size) {
        this.array = new Node[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Node("name" + i, new Random().nextInt(INDEX));
        }
    }

    public Node[] getArray() {
        return array;
    }

    public void setArray(Node[] array) {
        this.array = array;
    }

    public void indexCountSort() {
        int length = array.length;
        Node[] aux = new Node[length];
        int[] count = new int[INDEX + 1];
        for (Node node : array) {
            count[node.key + 1]++;
        }
        for (int i = 0; i < INDEX; i++) {
            count[i + 1] += count[i];
        }
        for (Node anArray : array) {
            aux[count[anArray.key]++] = new Node(anArray.name, anArray.key);
        }
        System.arraycopy(aux, 0, array, 0, length);
    }

    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i].key > array[i + 1].key) {
                return false;
            }
        }
        return true;
    }
}
