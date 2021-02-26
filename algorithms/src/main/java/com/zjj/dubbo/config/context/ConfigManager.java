package com.zjj.dubbo.config.context;

import com.zjj.dubbo.common.context.FrameworkExt;
import com.zjj.dubbo.common.context.LifecycleAdapter;
import com.zjj.dubbo.config.AbstractConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ConfigManager extends LifecycleAdapter implements FrameworkExt {
    public static final String NAME = "config";
    final Map<String, Map<String, AbstractConfig>> configsCache = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ConfigManager() {
    }


}
