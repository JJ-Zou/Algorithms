package com.zjj;

import com.zjj.config.ProtocolConfig;
import com.zjj.config.RegistryConfig;
import com.zjj.config.spring.annotation.EnableJRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@EnableJRpc
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean("protocol_1")
    public ProtocolConfig protocolConfig1() {
        ProtocolConfig protocol1 = new ProtocolConfig();
        protocol1.setProtocolName("jrpc");
        return protocol1;
    }

    @Bean("protocol_2")
    public ProtocolConfig protocolConfig2() {
        ProtocolConfig protocol2 = new ProtocolConfig();
        protocol2.setProtocolName("jrpc");
        return protocol2;
    }

    @Bean("registry_1")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setRegisterProtocol("zookeeper");
        registryConfig.setAddress("39.105.65.104");
        registryConfig.setPort(2181);
        return registryConfig;
    }

}
