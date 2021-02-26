package com.zjj.dubbo.registry;

import com.zjj.dubbo.common.Node;
import com.zjj.dubbo.common.URL;

public interface Registry extends Node, RegistryService {
    default void reExportRegister(URL url) {
        register(url);
    }

    default void reExportUnregister(URL url) {
        unregister(url);
    }
}
