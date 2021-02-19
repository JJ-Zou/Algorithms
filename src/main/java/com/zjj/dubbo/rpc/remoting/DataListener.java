package com.zjj.dubbo.rpc.remoting;

public interface DataListener {
    void dataChanged(String path, Object value, EventType eventType);
}
