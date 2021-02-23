package com.zjj.dubbo.demo.provider;

import com.zjj.dubbo.demo.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

@DubboService
public class DemoServiceImpl2 implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello annotation " + name;
    }
}
