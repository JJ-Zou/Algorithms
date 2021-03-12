package com.zjj.registry.support;

import com.zjj.common.JRpcURL;
import com.zjj.registry.Registry;
import com.zjj.registry.RegistryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public abstract class AbstractRegistryFactory implements RegistryFactory {
    private final ConcurrentMap<String, Registry> registries = new ConcurrentHashMap<>();

    @Override
    public Registry getRegistry(JRpcURL url) {
        String registryUri = url.getUri();
        return registries.computeIfAbsent(registryUri, r -> {
            Registry registry = createRegistry(url);
            log.info("create Registry: [{}] for uri: [{}] and put it into cache.", registry, registryUri);
            return registry;
        });
    }

    protected abstract Registry createRegistry(JRpcURL url);
}
