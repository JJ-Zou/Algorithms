package com.zjj.dubbo.rpc.remoting;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.registry.zookeeper.ZookeeperClient;

public interface ZookeeperTransporter {

    ZookeeperClient connect(URL url);
}
