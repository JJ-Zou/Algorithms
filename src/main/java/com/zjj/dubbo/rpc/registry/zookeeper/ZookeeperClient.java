package com.zjj.dubbo.rpc.registry.zookeeper;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.remoting.ChildListener;
import com.zjj.dubbo.rpc.remoting.DataListener;
import com.zjj.dubbo.rpc.remoting.StateListener;

import java.util.List;
import java.util.concurrent.Executor;

public interface ZookeeperClient {
    void create(String path, boolean ephemeral);

    void create(String path, String content, boolean ephemeral);

    String getContent(String path);

    void delete(String path);

    List<String> getChildren(String path);

    List<String> addChildrenListener(String path, ChildListener listener);

    void addDataListener(String path, DataListener listener);

    void addDataListener(String path, DataListener listener, Executor executor);

    void removeDataListener(String path, DataListener listener);

    void removeChildrenListener(String path, ChildListener listener);

    void addStateListener(StateListener listener);

    void removeStateListener(StateListener listener);

    boolean isConnected();

    void close();

    URL getUrl();
}
