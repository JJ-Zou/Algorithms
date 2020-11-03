package com.zjj.designPattern.strategy;

public class OneStrategy implements Strategy {
    @Override
    public void execute() {
        System.out.println("OneStrategy");
    }
}
