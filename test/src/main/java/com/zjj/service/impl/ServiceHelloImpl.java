package com.zjj.service.impl;

import com.zjj.service.ServiceHello;

public class ServiceHelloImpl implements ServiceHello {
    {
        System.out.println("ServiceHelloImpl被创建");
        System.out.println(this.hello("World"));
    }

    @Override
    public String hello(String value) {
        return "Hello " + value;
    }
}
