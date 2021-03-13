package com.zjj.service.test;

import com.zjj.config.MethodConfig;
import com.zjj.config.ProtocolConfig;
import com.zjj.config.ReferenceConfig;
import com.zjj.config.RegistryConfig;
import com.zjj.service.HelloService;

import java.lang.reflect.Field;
import java.util.Collections;

public class Test {
    private HelloService helloService;

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setRegisterProtocol("zookeeper");
        registryConfig.setAddress("39.105.65.104");
        registryConfig.setPort(2181);

        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("compute");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setDefault(true);
        protocolConfig.setProtocolName("jrpc");

        ReferenceConfig<HelloService> referenceConfig = new ReferenceConfig<>();

        referenceConfig.setRegistryConfigs(Collections.singletonList(registryConfig));
        referenceConfig.setInterfaceClass(HelloService.class);
        referenceConfig.setMethodConfigs(Collections.singletonList(methodConfig));
        referenceConfig.setProtocolConfigs(Collections.singletonList(protocolConfig));
        Object ref = referenceConfig.getRef();

        Test testReference = new Test();
        Field field = testReference.getClass().getDeclaredField("helloService");
        field.setAccessible(true);
        field.set(testReference, ref);
        long start = System.currentTimeMillis();
        System.out.println(testReference.helloService.compute("world"));
        long stop = System.currentTimeMillis();
        System.out.println((stop - start) + "ms");
    }
}
