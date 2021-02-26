package com.zjj.dubbo.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtils {
    private NetUtils() {

    }

    public static String getIpByHost(String hostname) {
        try {
            return InetAddress.getByName(hostname).getHostAddress();
        } catch (UnknownHostException e) {
            return hostname;
        }
    }
}
