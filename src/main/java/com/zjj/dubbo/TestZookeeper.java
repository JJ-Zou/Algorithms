package com.zjj.dubbo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.List;

@Slf4j
public class TestZookeeper {
    @SneakyThrows
    public static void main(String[] args) {
        try (CuratorFramework client = CuratorFrameworkFactory
                .newClient("39.105.65.104:2181", new RetryNTimes(1, 100))) {
            client.start();
            String path = "/dubbo/config/dubbo/dubbo.properties";

            List<String> strings = client.getChildren().forPath(path);
            log.info("{}: {}", path, strings);

//            client.delete().forPath(path);
//            List<String> strings = client.getChildren().forPath("/dubbo/config");
//            log.info("/dubbo/config: {}", strings);
//            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "dubbo.registry.address=zookeeper://39.105.65.104:2181dubbo.metadata-report.address=zookeeper://39.105.65.104:2181".getBytes());
//            List<String> strings1 = client.getChildren().forPath("/dubbo/config/dubbo/dubbo.properties");
//            log.info("/dubbo/config/dubbo/dubbo.properties: {}", strings1);
        }
    }
}
