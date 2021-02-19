package com.zjj.dubbo.rpc.registry.support;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.registry.Registry;
import com.zjj.dubbo.rpc.registry.zookeeper.ZookeeperRegistry;
import com.zjj.dubbo.rpc.remoting.ZookeeperTransporter;

public class ZookeeperRegistryFactory extends AbstractRegistryFactory {

    private ZookeeperTransporter zookeeperTransporter;

    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }

    @Override
    protected Registry createRegistry(URL url) {
        return new ZookeeperRegistry(url, zookeeperTransporter);
    }
}
