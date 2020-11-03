package com.zjj.designPattern.factory;

/**
 * 工厂方法，针对一个产品等级结构，每个具体工厂类只创建一个具体产品类
 */
public class Test {
    public static void main(String[] args) {
        ProductAFactory productAFactory = new ProductAFactory();
        ProductBFactory productBFactory = new ProductBFactory();
        Product productA = productAFactory.createProduct();
        productA.doStuff();
        Product productB = productBFactory.createProduct();
        productB.doStuff();
    }
}
