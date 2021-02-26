package com.zjj.dubbo.remoting.zookeeper;

import com.zjj.dubbo.common.URL;
import com.zjj.dubbo.remoting.zookeeper.ZookeeperClient;

public interface ZookeeperTransporter {

    ZookeeperClient connect(URL url);
}
