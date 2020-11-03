package com.zjj.designPattern.abstractFactory;

/**
 * 抽象工厂，多个抽象产品类，面向多个产品等级结构，产品族
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory appleFactory = new AppleFactory();
        Computer computer = appleFactory.createComputer();
        Phone phone = appleFactory.createPhone();
        computer.work();
        phone.call();

        AbstractFactory miFactory = new MiFactory();
        Computer computer1 = miFactory.createComputer();
        Phone phone1 = miFactory.createPhone();
        computer1.work();
        phone1.call();
    }
}
