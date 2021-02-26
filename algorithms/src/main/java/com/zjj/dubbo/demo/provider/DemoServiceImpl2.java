package com.zjj.dubbo.demo.provider;

import com.zjj.dubbo.demo.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl2 implements DemoService {
    {
        System.out.println(this + " 被创建");
    }

    @Override
    public String sayHello(String name) {
        return "Hello annotation " + name;
    }
}
