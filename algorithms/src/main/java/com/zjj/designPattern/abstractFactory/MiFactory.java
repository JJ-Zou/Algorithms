package com.zjj.designPattern.abstractFactory;

public class MiFactory implements AbstractFactory {
    @Override
    public Computer createComputer() {
        return new MiComputer();
    }

    @Override
    public Phone createPhone() {
        return new MiPhone();
    }
}
