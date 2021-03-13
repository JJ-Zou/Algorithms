package com.zjj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class HelloReference2 {

    @Autowired
    private ThreadPoolTaskExecutor executor;
}
