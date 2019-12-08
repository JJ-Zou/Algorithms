package com.ZJJ.Node.SingleListNode;

import com.ZJJ.Node.Node;

/**
 * 单链表
 * @param <T>
 */
public class SingleListNode<T> {

    private Node head;
    private int size;

    public SingleListNode(Node head) {
        this.head = head;
        size = 1;
    }

    public int size(){
        return size;
    }
    /**
     * 在链表index索引位置增加一个结点
     * @param value
     * @param index
     */
    public void addNode(T value,int index) {
        Node insert = new Node(value);
        if(index <= 0){
            insert.setNext(head);
            head = insert;
        } else if (index >= size) {
            Node cur = findNode(size-1);
            cur.setNext(insert);
        } else {
            Node pre = findNode(index-1);
            insert.setNext(pre.getNext());
            pre.setNext(insert);
        }
        size++;
    }

    /**
     * 删除链表index索引位置的结点
     * @param index
     * @return
     */
    public T deleteNode(int index){
        if(index < 0 || index > size-1){
            throw new RuntimeException("索引不合法！");
        }
        if(index == 0){
            T val = (T) head.getValue();
            head = head.getNext();
            size--;
            return val;
        } else {
            Node pre = findNode(index-1);
            T value = (T) pre.getNext().getValue();
            pre.setNext(pre.getNext().getNext());
            return value;
        }
    }

    /**
     * 将链表index索引位置的值修改为value
     * @param value
     * @param index
     * @return
     */
    public void updateNode(T value,int index) {
        if(index < 0 || index > size-1){
            throw new RuntimeException("索引不合法！");
        }
        Node cur = findNode(index);
        cur.setValue(value);
    }

    /**
     * 查找链表index索引位置的结点
     * @param index
     * @return
     */
    public Node findNode(int index) {
        if(size == 0){
            return null;
        }
        if(index < 0 || index > size-1){
            throw new RuntimeException("索引不合法！");
        }
        Node cur = head;
        for(int i=0;i<index;i++){
            cur = cur.getNext();
        }
        return cur;
    }

    @Override
    public String toString() {
        return head == null ? null : head.toString();
    }
}
