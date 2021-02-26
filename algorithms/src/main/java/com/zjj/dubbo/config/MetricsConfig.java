package com.zjj.dubbo.config;

import com.zjj.dubbo.common.constants.CommonConstants;
import com.zjj.dubbo.config.support.Parameter;

public class MetricsConfig extends AbstractConfig {
    private static final long serialVersionUID = -8471182917512989736L;
    private String port;
    private String protocol;

    public MetricsConfig() {
    }

    @Parameter(key = CommonConstants.METRICS_PORT)
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Parameter(key = CommonConstants.METRICS_PROTOCOL)
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
