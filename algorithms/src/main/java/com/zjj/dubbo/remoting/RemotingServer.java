package com.zjj.dubbo.remoting;

import com.zjj.dubbo.common.Resetable;

import java.net.InetSocketAddress;
import java.util.Collection;

public interface RemotingServer extends Endpoint, Resetable, IdleSensible {
    boolean isBound();

    Collection<Channel> getChannels();

    Channel getChannel(InetSocketAddress remoteAddress);

}
