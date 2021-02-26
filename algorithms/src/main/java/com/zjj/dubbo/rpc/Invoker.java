package com.zjj.dubbo.rpc;

import com.zjj.dubbo.common.Node;

public interface Invoker<T> extends Node {
    Class<T> getInterface();

    Result invoke(Invocation invocation) throws RpcException;

}
