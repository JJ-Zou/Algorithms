package com.zjj.service.impl;

import com.zjj.config.spring.annotation.JRpcReference;
import com.zjj.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class HelloReference {
//    @JRpcReference
    private HelloService helloService;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public String toString() {
        return "HelloReference{" +
                "helloService=" + helloService +
                ", executor=" + executor +
                '}';
    }
}
