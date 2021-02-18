package com.zjj.dubbo.rpc.common;

public interface Node {
    URL getUrl();

    boolean isAvailable();

    void destroy();
}
