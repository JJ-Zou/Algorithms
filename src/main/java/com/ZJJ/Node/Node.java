package com.ZJJ.Node;

public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(){

    }
    public Node(T value) {
        this.value = value;
    }

    public Node(T value,Node<T> next) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        if(this.value == null){
            this.value = next.getValue();
        }else {
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> cur = next;
        if(value != null){
            sb.append(value).append("->");
        }
        while(cur != null) {
            T temp = cur.getValue();
            sb.append(temp).append("->");
            cur = cur.getNext();
        }
        sb.append(cur);
        return sb.toString();

        /**
         * 递归会导致栈溢出
         */
//        return value == null ? null : value +"->" + next ;
    }
}
