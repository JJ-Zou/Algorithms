package com.zjj.avltree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AvlTree {
    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < 10; i++) {
            avlTree.add(i);
        }
        System.out.println(avlTree.levelTravel());
    }

    static class Node {
        int value;
        int height;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public AvlTree() {
    }

    public int search(int value) {
        Node node = search(root, value);
        return node == null ? -1 : node.value;
    }

    private Node search(Node r, int value) {
        if (r == null) {
            return null;
        }
        if (value < r.value) {
            return search(r.left, value);
        }
        if (value > r.value) {
            return search(r.right, value);
        }
        return r;
    }

    public void add(int value) {
        root = insert(root, value);
        root.parent = null;
    }

    private Node insert(Node r, int value) {
        if (r == null) {
            r = new Node(value);
        } else if (value < r.value) {
            r.left = insert(r.left, value);
            r.left.parent = r.left;
            if (height(r.left) - height(r.right) == 2) {
                if (value < r.left.value) {
                    r = leftLeftRotate(r);
                } else if (value > r.left.value) {
                    r = leftRightRotate(r);
                }
            }
        } else if (value > r.value) {
            r.right = insert(r.right, value);
            r.right.parent = r.right;
            if (height(r.right) - height(r.left) == 2) {
                if (value > r.right.value) {
                    r = rightRightRotate(r);
                } else if (value < r.right.value) {
                    r = rightLeftRotate(r);
                }
            }
        }
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return r;
    }

    private Node rightRightRotate(Node r) {
        Node right = r.right;
        r.right = right.left;
        if (r.right != null) {
            r.right.parent = r;
        }
        right.left = r;
        r.parent = right;
        right.height = Math.max(height(right.left), height(right.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return right;
    }

    private Node leftLeftRotate(Node r) {
        Node left = r.left;
        r.left = left.right;
        if (r.left != null) {
            r.left.parent = r;
        }
        left.right = r;
        r.parent = left;
        left.height = Math.max(height(left.left), height(left.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return left;
    }

    private Node leftRightRotate(Node r) {
        r = leftLeftRotate(r.left);
        return rightRightRotate(r);
    }

    private Node rightLeftRotate(Node r) {
        r = rightRightRotate(r.right);
        return leftLeftRotate(r);
    }

    private int height(Node r) {
        return r == null ? 0 : r.height;
    }

    public List<Integer> levelTravel() {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.pollFirst();
                if (node == null) {
                    res.add(null);
                } else {
                    res.add(node.value);
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                }
            }
        }
        return res;
    }
}
