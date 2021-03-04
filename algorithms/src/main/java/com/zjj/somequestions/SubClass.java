package com.zjj.somequestions;

public class SubClass extends FatherClass {
    public static void main(String[] args) {
        FatherClass subClass = new SubClass();
        ((SubClass) subClass).force();
    }

    public void force() {
        System.out.println("force");
        super.offer();
    }

    @Override
    public void offer() {
        System.out.println("SubClass offer");
        super.offer();
    }
}
