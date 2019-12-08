package com.ZJJ.Node.SingleListNode;

public class SingleListNode<T> {
    T value;
    SingleListNode next;

    public SingleListNode(){

    }
    public SingleListNode(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value +"->" + next ;
    }
}
