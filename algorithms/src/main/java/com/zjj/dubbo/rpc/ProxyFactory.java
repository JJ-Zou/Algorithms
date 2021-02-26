package com.zjj.dubbo.rpc;


import com.zjj.dubbo.common.URL;

public interface ProxyFactory {

    <T> T getProxy(Invoker<T> invoker) throws RpcException;

    <T> T getProxy(Invoker<T> invoker, boolean generic) throws RpcException;

    <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException;


}
