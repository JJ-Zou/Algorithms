package com.zjj.dubbo.config;

import com.zjj.dubbo.common.RemotingConstants;
import com.zjj.dubbo.common.URL;
import com.zjj.dubbo.common.utils.PojoUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

public class RegistryConfig extends AbstractConfig {
    public static final String NO_AVAILABLE = "N/A";
    private static final long serialVersionUID = 6807366295053407447L;
    private String address;
    private String username;
    private String password;
    private Integer port;
    private String protocol;
    private String transporter;
    private String server;
    private String client;
    private String cluster;
    private String zone;
    private String group;
    private String version;
    private Integer timeout;
    private Integer session;
    private String file;
    private Integer wait;
    private Boolean check;
    private Boolean dynamic;
    private Boolean register;
    private Boolean subscribe;
    private Map<String, String> parameters;
    private Boolean isDefault;
    private Boolean simplified;
    private String extraKeys;
    private Boolean useAsConfigCenter;
    private Boolean useAsMetadataCenter;
    private String accepts;
    private Boolean preferred;
    private Integer weight;

    public RegistryConfig() {
    }

    public RegistryConfig(String address) {
        setAddress(address);
    }

    public RegistryConfig(String address, String protocol) {
        setAddress(address);
        setProtocol(protocol);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        if (address != null) {
            try {
                URL url = URL.valueOf(address);
                PojoUtils.updatePropertyIfAbsent(this::getUsername, this::setUsername, url.getUsername());
                PojoUtils.updatePropertyIfAbsent(this::getPassword, this::setPassword, url.getPassword());
                PojoUtils.updatePropertyIfAbsent(this::getProtocol, this::setProtocol, url.getProtocol());
                PojoUtils.updatePropertyIfAbsent(this::getPort, this::setPort, url.getPort());
                Map<String, String> params = url.getParameters();
                if (!CollectionUtils.isEmpty(params)) {
                    params.remove(RemotingConstants.BACKUP_KEY);
                }
                updateParameters(params);
            } catch (Exception ignored) {
            }
        }
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public Boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Boolean isRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void updateParameters(Map<String, String> parameters) {
        if (CollectionUtils.isEmpty(parameters)) {
            return;
        }
        if (this.parameters == null) {
            this.parameters = parameters;
        } else {
            this.parameters.putAll(parameters);
        }
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getSimplified() {
        return simplified;
    }

    public void setSimplified(Boolean simplified) {
        this.simplified = simplified;
    }

    public String getExtraKeys() {
        return extraKeys;
    }

    public void setExtraKeys(String extraKeys) {
        this.extraKeys = extraKeys;
    }

    public Boolean getUseAsConfigCenter() {
        return useAsConfigCenter;
    }

    public void setUseAsConfigCenter(Boolean useAsConfigCenter) {
        this.useAsConfigCenter = useAsConfigCenter;
    }

    public Boolean getUseAsMetadataCenter() {
        return useAsMetadataCenter;
    }

    public void setUseAsMetadataCenter(Boolean useAsMetadataCenter) {
        this.useAsMetadataCenter = useAsMetadataCenter;
    }

    public String getAccepts() {
        return accepts;
    }

    public void setAccepts(String accepts) {
        this.accepts = accepts;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public void refresh() {
        super.refresh();
        if (!StringUtils.isEmpty(this.getId())) {
            this.setPrefix(Constants.REGISTRIES_SUFFIX);
            super.refresh();
        }
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(address);
    }
}
