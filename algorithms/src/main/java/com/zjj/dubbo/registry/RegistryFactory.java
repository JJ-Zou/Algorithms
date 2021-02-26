package com.zjj.dubbo.registry;

import com.zjj.dubbo.common.URL;

public interface RegistryFactory {
    Registry getRegistry(URL url);
}
