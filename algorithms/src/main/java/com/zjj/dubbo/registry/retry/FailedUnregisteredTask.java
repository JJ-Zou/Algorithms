package com.zjj.dubbo.registry.retry;

import com.zjj.dubbo.common.URL;
import com.zjj.dubbo.registry.Constants;
import com.zjj.dubbo.registry.support.FailbackRegistry;
import com.zjj.dubbo.common.timer.Timeout;

public class FailedUnregisteredTask extends AbstractRetryTask {
    public FailedUnregisteredTask(URL url, FailbackRegistry registry) {
        super(url, registry, Constants.RETRY_UNREGISTER_NAME);
    }

    @Override
    protected void doRetry(URL url, FailbackRegistry registry, Timeout timeout) {
        registry.doUnregister(url);
        registry.removeFailedUnregisteredTask(url);
    }
}
