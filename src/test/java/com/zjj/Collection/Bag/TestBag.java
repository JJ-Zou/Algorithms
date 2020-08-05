package com.zjj.Collection.Bag;

import org.junit.Test;

public class TestBag {
    @Test
    public void test() throws Exception {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        System.out.println(bag.getFirst());
        for (int i : bag) {
            System.out.print(i + " ");
        }
    }
}
