package com.zjj.dubbo.rpc.registry;

import com.zjj.dubbo.rpc.common.URL;

import java.util.List;

public interface NotifyListener {
    void notify(List<URL> urls);
}
