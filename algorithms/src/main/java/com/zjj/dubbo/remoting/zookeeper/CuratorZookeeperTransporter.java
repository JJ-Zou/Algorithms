package com.zjj.dubbo.remoting.zookeeper;

import com.zjj.dubbo.common.URL;
import com.zjj.dubbo.remoting.support.AbstractZookeeperTransporter;

public class CuratorZookeeperTransporter extends AbstractZookeeperTransporter {
    @Override
    protected ZookeeperClient createZookeeperClient(URL url) {
        return new CuratorZookeeperClient(url);
    }
}
