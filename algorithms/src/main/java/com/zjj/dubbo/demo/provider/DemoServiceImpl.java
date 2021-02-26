package com.zjj.dubbo.demo.provider;

import com.zjj.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService {
    {
        System.out.println(this + " 被创建");
    }

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
