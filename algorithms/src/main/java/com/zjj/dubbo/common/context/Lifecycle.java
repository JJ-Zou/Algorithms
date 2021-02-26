package com.zjj.dubbo.common.context;

public interface Lifecycle {
    void initialize() throws IllegalStateException;

    void start() throws IllegalStateException;

    void destroy() throws IllegalStateException;
}
