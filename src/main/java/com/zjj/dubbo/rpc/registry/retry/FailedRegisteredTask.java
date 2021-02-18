package com.zjj.dubbo.rpc.registry.retry;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.common.timer.Timeout;
import com.zjj.dubbo.rpc.registry.support.FailbackRegistry;

import static com.zjj.dubbo.rpc.registry.Constants.RETRY_REGISTER_NAME;

public class FailedRegisteredTask extends AbstractRetryTask {
    public FailedRegisteredTask(URL url, FailbackRegistry registry) {
        super(url, registry, RETRY_REGISTER_NAME);
    }

    @Override
    protected void doRetry(URL url, FailbackRegistry registry, Timeout timeout) {
        registry.doRegister(url);
        registry.removeFailedRegisteredTask(url);
    }
}
