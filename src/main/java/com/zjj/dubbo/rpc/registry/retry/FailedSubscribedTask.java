package com.zjj.dubbo.rpc.registry.retry;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.common.timer.Timeout;
import com.zjj.dubbo.rpc.registry.NotifyListener;
import com.zjj.dubbo.rpc.registry.support.FailbackRegistry;

import static com.zjj.dubbo.rpc.registry.Constants.RETRY_SUBSCRIBE_NAME;

public class FailedSubscribedTask extends AbstractRetryTask {
    private final NotifyListener listener;

    public FailedSubscribedTask(URL url, FailbackRegistry registry, NotifyListener listener) {
        super(url, registry, RETRY_SUBSCRIBE_NAME);
        if (listener == null) {
            throw new IllegalArgumentException();
        }
        this.listener = listener;
    }

    @Override
    protected void doRetry(URL url, FailbackRegistry registry, Timeout timeout) {
        registry.doSubscribe(url, listener);
        registry.removeFailedSubscribedTask(url, listener);
    }
}
