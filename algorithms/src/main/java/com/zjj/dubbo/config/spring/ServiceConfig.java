package com.zjj.dubbo.config.spring;

import com.zjj.dubbo.common.extension.ExtensionLoader;
import com.zjj.dubbo.common.utils.NameThreadFactory;
import com.zjj.dubbo.common.utils.StringUtils;
import com.zjj.dubbo.config.ServiceConfigBase;
import com.zjj.dubbo.config.annotation.RpcService;
import com.zjj.dubbo.config.bootstrap.RpcBootstrap;
import com.zjj.dubbo.rpc.Exporter;
import com.zjj.dubbo.rpc.Protocol;
import com.zjj.dubbo.rpc.ProxyFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@Slf4j
public class ServiceConfig<T> extends ServiceConfigBase<T> {
    private static final long serialVersionUID = -7069760400254809823L;
    private static final Map<String, Integer> RANDOM_PORT_MAP = new HashMap<>();
    private static final ScheduledExecutorService DELAY_EXPORT_EXECUTOR =
            Executors.newSingleThreadScheduledExecutor(new NameThreadFactory("RpcServiceDelayExporter", true));
    private static final Protocol PROTOCOL = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private static final ProxyFactory PROXY_FACTORY = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
    private transient volatile boolean exported;
    private transient volatile boolean unexported;
    private RpcBootstrap bootstrap;
    private final List<Exporter<?>> exporters = new ArrayList<>();

    public ServiceConfig() {

    }

    public ServiceConfig(RpcService service) {
        super(service);
    }

    @Override
    public boolean isExported() {
        return exported;
    }

    @Override
    public boolean isUnexported() {
        return unexported;
    }

    @Override
    public synchronized void export() {
        if (bootstrap == null) {
            bootstrap = RpcBootstrap.getInstance();
            bootstrap.initialize();
        }
        doExport();
    }

    protected synchronized void doExport() {
        if (unexported) {
            throw new IllegalStateException("The com.zjj.service " + interfaceClass.getName() + " has already unexported!");
        }
        if (exported) {
            return;
        }
        exported = true;
        if (StringUtils.isEmpty(path)) {
            path = interfaceName;
        }
        doExportUrls();
    }

    private void doExportUrls() {
    }

    @Override
    public void unexport() {
        if (!exported || unexported) {
            return;
        }
        if (!exporters.isEmpty()) {
            for (Exporter<?> exporter : exporters) {
                try {
                    exporter.unexport();
                } catch (Throwable t) {
                    log.warn("Unexpected error occured when unexport {}.", exporter, t);
                }
            }
            exporters.clear();
        }
        unexported = true;
    }

    public RpcBootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(RpcBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }
}
