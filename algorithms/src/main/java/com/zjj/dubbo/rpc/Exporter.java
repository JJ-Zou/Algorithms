package com.zjj.dubbo.rpc;

public interface Exporter<T> {
    Invoker<T> getInvoker();

    void unexport();

}
