package com.zjj.dubbo.rpc.registry.retry;

import com.zjj.dubbo.rpc.common.URL;
import com.zjj.dubbo.rpc.common.timer.Timeout;
import com.zjj.dubbo.rpc.registry.NotifyListener;
import com.zjj.dubbo.rpc.registry.support.FailbackRegistry;

import static com.zjj.dubbo.rpc.registry.Constants.RETRY_UNSUBSCRIBE_NAME;

public class FailedUnsubscribedTask extends AbstractRetryTask {
    private final NotifyListener listener;

    public FailedUnsubscribedTask(URL url, FailbackRegistry registry, NotifyListener listener) {
        super(url, registry, RETRY_UNSUBSCRIBE_NAME);
        if (listener == null) {
            throw new IllegalArgumentException();
        }
        this.listener = listener;
    }

    @Override
    protected void doRetry(URL url, FailbackRegistry registry, Timeout timeout) {
        registry.doUnsubscribe(url, listener);
        registry.removeFailedUnsubscribedTask(url, listener);
    }
}
