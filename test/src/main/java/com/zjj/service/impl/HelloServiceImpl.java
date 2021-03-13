package com.zjj.service.impl;

import com.zjj.config.spring.annotation.JRpcService;
import com.zjj.service.HelloService;

@JRpcService(exportProtocol = "protocol_1:11290",
        registry = "registry_1")
public class HelloServiceImpl implements HelloService {
    @Override
    public String compute(String input) {
        return "hello " + input;
    }
}
