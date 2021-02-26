package com.zjj.dubbo.rpc;

import com.zjj.dubbo.common.URL;
import com.zjj.dubbo.remoting.RemotingServer;

public interface ProtocolServer {
    String getAddress();

    void setAddress(String address);

    void close();

    default URL getUrl() {
        return null;
    }

    default void reset(URL url) {
    }


    default RemotingServer getRemotingServer() {
        return null;
    }

    default void setRemotingServers(RemotingServer server) {
    }
}
