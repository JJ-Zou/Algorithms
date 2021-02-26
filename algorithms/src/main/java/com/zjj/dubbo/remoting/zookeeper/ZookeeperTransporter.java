package com.zjj.dubbo.remoting.zookeeper;

import com.zjj.dubbo.common.URL;

public interface ZookeeperTransporter {

    ZookeeperClient connect(URL url);
}
