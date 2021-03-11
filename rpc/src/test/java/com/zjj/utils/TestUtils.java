package com.zjj.utils;

import com.zjj.common.utils.NetUtils;
import com.zjj.common.utils.ReflectUtils;
import com.zjj.exception.AbstractJRpcException;
import com.zjj.exception.JRpcFrameworkException;
import com.zjj.transport.EndpointFactory;
import io.netty.util.NetUtil;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;

public class TestUtils {
    @Test
    public void getMethodSign() {
        for (Method method : EndpointFactory.class.getMethods()) {
            if (method.getDeclaringClass() == EndpointFactory.class) {
                System.out.println(ReflectUtils.getMethodSign(method));
            }
        }
    }

    @Test
    public void isAssignFrom() {
        System.out.println(AbstractJRpcException.class.isAssignableFrom(JRpcFrameworkException.class));
        System.out.println(JRpcFrameworkException.class.isAssignableFrom(AbstractJRpcException.class));
        System.out.println(JRpcFrameworkException.class.isAssignableFrom(RuntimeException.class));
        System.out.println(RuntimeException.class.isAssignableFrom(JRpcFrameworkException.class));
    }

    @Test
    public void localhost() throws UnknownHostException {
        System.out.println(NetUtils.getLocalHostString());
//        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

    @Test
    public void netInterface() throws SocketException {
        Collections.list(NetworkInterface.getNetworkInterfaces());
    }
}