package com.zjj.dubbo.config;

import lombok.Data;

@Data
public class ProviderConfig extends AbstractServiceConfig {
    private static final long serialVersionUID = -8963227160557193699L;
    private String host;
    private Integer port;
    private String contextpath;
    private String threadpool;
    private String threadname;
    private Integer threads;
    private Integer iothreads;
    private Integer queues;
    private Integer accepts;
    private String codec;
    private String charset;
    private Integer payload;
    private Integer buffer;
    private String transporter;
    private String exchanger;
    private String dispatcher;
    private String networker;
    private String server;
    private String client;
    private String telnet;
    private String prompt;
    private String status;
    private Integer wait;
    private Boolean isDefault;


}
