package com.zjj.designPattern.abstractFactory;

public class AppleFactory implements AbstractFactory {
    @Override
    public Computer createComputer() {
        return new IMac();
    }

    @Override
    public Phone createPhone() {
        return new IPhone();
    }
}
