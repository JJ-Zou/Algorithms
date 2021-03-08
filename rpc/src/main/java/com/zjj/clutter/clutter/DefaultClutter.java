package com.zjj.clutter.clutter;

import com.zjj.clutter.Clutter;
import com.zjj.clutter.HaStrategy;
import com.zjj.clutter.LoadBalance;
import com.zjj.common.JRpcURL;
import com.zjj.common.JRpcURLParamType;
import com.zjj.exception.AbstractJRpcException;
import com.zjj.exception.JRpcServiceConsumerException;
import com.zjj.exception.JRpcServiceProviderException;
import com.zjj.rpc.Reference;
import com.zjj.rpc.Request;
import com.zjj.rpc.Response;
import com.zjj.rpc.message.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class DefaultClutter<T> implements Clutter<T> {

    private final AtomicBoolean available = new AtomicBoolean(false);

    private HaStrategy<T> haStrategy;
    private LoadBalance<T> loadBalance;
    private List<Reference<T>> references;
    private JRpcURL url;


    @Override
    public void init() {
        onRefresh(references);
        available.set(true);
    }


    @Override
    public void onRefresh(List<Reference<T>> references) {
        if (CollectionUtils.isEmpty(references)) {
            log.warn("No reference can be used.");
            return;
        }
        loadBalance.onRefresh(references);
        List<Reference<T>> old = this.references;
        this.references = references;
        haStrategy.setUrl(url);
        if (CollectionUtils.isEmpty(old)) {
            return;
        }
        old.removeAll(references);
        ClutterDestroy.delayDestroy(old);
    }


    @Override
    public Response call(Request request) {
        if (!isAvailable()) {
            throw new IllegalStateException("Clutter cannot available for request " + request + ", service not found.");
        }
        try {
            return haStrategy.call(request, loadBalance);
        } catch (Exception e) {
            return callFalse(request, e);
        }
    }

    private Response callFalse(Request request, Exception e) {
        if (e instanceof JRpcServiceConsumerException) {
            throw (RuntimeException) e;
        }
        boolean thrown = getUrl().getParameter(JRpcURLParamType.throwException.getName(), JRpcURLParamType.throwException.isBooleanValue());
        if (thrown) {
            if (e instanceof AbstractJRpcException) {
                throw (AbstractJRpcException) e;
            } else {
                throw new JRpcServiceProviderException(e);
            }
        }
        return DefaultResponse.builder().exception(e).requestId(request.getRequestId()).protocolVersion(request.getProtocolVersion()).build();
    }

    @Override
    public String desc() {
        return toString();
    }

    @Override
    public void destroy() {
        references.forEach(Reference::destroy);
        available.set(false);
    }

    @Override
    public boolean isAvailable() {
        return available.get();
    }

    @Override
    public JRpcURL getUrl() {
        return url;
    }

    @Override
    public Class<T> getInterface() {
        if (references == null || references.isEmpty()) {
            return null;
        }
        return references.get(0).getInterface();
    }


    @Override
    public LoadBalance<T> getLoadBalance() {
        return loadBalance;
    }

    @Override
    public List<Reference<T>> getReferences() {
        return references;
    }

    @Override
    public void setUrl(JRpcURL url) {
        this.url = url;
    }

    @Override
    public void setLoadBalance(LoadBalance<T> loadBalance) {
        this.loadBalance = loadBalance;
    }

    @Override
    public void setHaStrategy(HaStrategy<T> haStrategy) {
        this.haStrategy = haStrategy;
    }

    @Override
    public String toString() {
        return "DefaultClutter{" +
                "available=" + available +
                ", haStrategy=" + haStrategy +
                ", loadBalance=" + loadBalance +
                ", references=" + references +
                ", url=" + url +
                '}';
    }

    private static class ClutterDestroy {

        private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(10);

        private static final int DELAY_TIME = 1000;

        public static <T> void delayDestroy(List<Reference<T>> references) {
            references.forEach(r -> EXECUTOR.schedule(r::destroy, DELAY_TIME, TimeUnit.MILLISECONDS));
        }

    }
}
