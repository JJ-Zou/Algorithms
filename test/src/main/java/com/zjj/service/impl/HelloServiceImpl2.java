package com.zjj.service.impl;

import com.zjj.config.spring.annotation.JRpcService;
import com.zjj.service.WorldService;

@JRpcService(exportProtocol = "protocol_2:11990",
        registry = "registry_1")
public class HelloServiceImpl2 implements WorldService {
    @Override
    public String compute(String input) {
        return "hello " + input;
    }
}
