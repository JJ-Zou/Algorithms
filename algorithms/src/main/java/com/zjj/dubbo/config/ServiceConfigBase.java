package com.zjj.dubbo.config;

import com.zjj.dubbo.config.annotation.RpcService;

public abstract class ServiceConfigBase<T> extends AbstractServiceConfig {
    private static final long serialVersionUID = 5816559973305747329L;

    protected String interfaceName;
    protected Class<?> interfaceClass;
    protected T ref;
    protected String path;
    protected String providerIds;
    protected volatile String generic;

    public ServiceConfigBase() {
    }

    public ServiceConfigBase(RpcService service) {

    }

    public abstract void export();

    public abstract void unexport();

    public abstract boolean isExported();

    public abstract boolean isUnexported();
}
