package com.zjj.rpc.config.configcenter;

import com.zjj.rpc.common.JRpcURL;
import com.zjj.rpc.extension.ServiceExtensionLoader;

public interface DynamicConfiguration extends AutoCloseable {
    static DynamicConfiguration getDynamicConfiguration(JRpcURL connectionURL) {
        String protocol = connectionURL.getProtocol();
        com.zjj.rpc.config.configcenter.DynamicConfigurationFactory factory = getDynamicConfigurationFactory(protocol);
        return factory.getDynamicConfiguration(connectionURL);
    }

    static com.zjj.rpc.config.configcenter.DynamicConfigurationFactory getDynamicConfigurationFactory(String protocol) {
        com.zjj.rpc.config.configcenter.DynamicConfigurationFactory instance = ServiceExtensionLoader
                .getExtensionLoader(com.zjj.rpc.config.configcenter.DynamicConfigurationFactory.class)
                .getOrDefaultExtension(protocol);
        if (instance == null) {
            throw new IllegalStateException("Cannot find impl of DynamicConfigurationFactory.");
        }
        return instance;
    }

}
