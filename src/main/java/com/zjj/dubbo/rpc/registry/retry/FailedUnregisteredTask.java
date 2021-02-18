package com.zjj.dubbo.rpc.registry.retry;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.common.timer.Timeout;
import com.zjj.dubbo.rpc.registry.support.FailbackRegistry;

import static com.zjj.dubbo.rpc.registry.Constants.RETRY_UNREGISTER_NAME;

public class FailedUnregisteredTask extends AbstractRetryTask {
    public FailedUnregisteredTask(URL url, FailbackRegistry registry) {
        super(url, registry, RETRY_UNREGISTER_NAME);
    }

    @Override
    protected void doRetry(URL url, FailbackRegistry registry, Timeout timeout) {
        registry.doUnregister(url);
        registry.removeFailedUnregisteredTask(url);
    }
}
