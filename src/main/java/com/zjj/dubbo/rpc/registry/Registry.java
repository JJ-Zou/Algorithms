package com.zjj.dubbo.rpc.registry;

import com.zjj.dubbo.rpc.common.Node;
import com.zjj.dubbo.rpc.common.URL;

public interface Registry extends Node, RegistryService {
    default void reExportRegister(URL url) {
        register(url);
    }

    default void reExportUnregister(URL url) {
        unregister(url);
    }
}
