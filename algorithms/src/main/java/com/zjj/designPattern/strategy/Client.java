package com.zjj.designPattern.strategy;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        OneStrategy oneStrategy = new OneStrategy();
        context.setStrategy(oneStrategy);
        context.doSomeThing();
        TwoStrategy twoStrategy = new TwoStrategy();
        context.setStrategy(twoStrategy);
        context.doSomeThing();
        context.setStrategy(() -> System.out.println("lambda strategy"));
        context.doSomeThing();
    }
}
