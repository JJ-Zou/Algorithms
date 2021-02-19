package com.zjj.dubbo.rpc.registry;

import com.zjj.dubbo.rpc.common.URL;

public interface RegistryFactory {
    Registry getRegistry(URL url);
}
