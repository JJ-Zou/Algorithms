package com.zjj.dubbo.rpc;

import com.zjj.dubbo.common.URL;

import java.util.Collections;
import java.util.List;

public interface Protocol {
    int getDefaultPort();

    <T> Exporter<T> export(Invoker<T> invoker) throws RpcException;

    <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;

    void destroy();

    default List<ProtocolServer> getServers() {
        return Collections.emptyList();
    }

}
