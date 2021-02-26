package com.zjj.dubbo.common;

public interface Node {
    URL getUrl();

    boolean isAvailable();

    void destroy();
}
