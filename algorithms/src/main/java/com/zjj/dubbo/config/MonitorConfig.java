package com.zjj.dubbo.config;

import com.zjj.dubbo.common.constants.RegistryConstants;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Map;

@Data
public class MonitorConfig extends AbstractConfig {
    private static final long serialVersionUID = -6484162673861696442L;
    private String protocol;
    private String address;
    private String username;
    private String password;
    private String group;
    private String version;
    private String interval;
    private Map<String, String> parameters;
    private Boolean isDefault;

    public MonitorConfig() {
    }

    public MonitorConfig(String address) {
        this.address = address;
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(address) || RegistryConstants.REGISTRY_PROTOCOL.equals(protocol);
    }
}
