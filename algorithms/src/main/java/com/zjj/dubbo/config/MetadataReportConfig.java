package com.zjj.dubbo.config;

import com.zjj.dubbo.common.URL;
import com.zjj.dubbo.common.constants.CommonConstants;
import com.zjj.dubbo.common.utils.StringUtils;
import com.zjj.dubbo.config.support.Parameter;

import java.util.HashMap;
import java.util.Map;

public class MetadataReportConfig extends AbstractConfig {
    private static final long serialVersionUID = 7224697120113143082L;
    private static final String PREFIX_TAG = StringUtils.camelToSplitName(
            MetadataReportConfig.class.getSimpleName().substring(0, MetadataReportConfig.class.getSimpleName().length() - 6),
            CommonConstants.PROPERTIES_CHAR_SEPARATOR);
    private String address;
    private String username;
    private String password;
    private Integer timeout;
    private String group;
    private Map<String, String> parameters;

    private Integer retryTimes;

    private Integer retryPeriod;
    private Boolean cycleReport;
    private Boolean syncReport;
    private Boolean cluster;

    public MetadataReportConfig() {
    }

    public MetadataReportConfig(String address) {
        setAddress(address);
    }

    @Parameter(excluded = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public URL toUrl() throws IllegalArgumentException {
        String address = this.getAddress();
        if (StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException("The address of metadata report is invalid.");
        }
        URL url = URL.valueOf(address);
        Map<String, String> map = new HashMap<>(url.getParameters());
        appendParameters(map, this);
        map.putAll(convert(map, null));
        map.put("metadata", url.getProtocol());
        return new URL("metadata", url.getUsername(), url.getPassword(), url.getHost(),
                url.getPort(), url.getPath(), map);

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

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Parameter(key = "retry-times")
    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    @Parameter(key = "retry-period")
    public Integer getRetryPeriod() {
        return retryPeriod;
    }

    public void setRetryPeriod(Integer retryPeriod) {
        this.retryPeriod = retryPeriod;
    }

    @Parameter(key = "cycle-report")
    public Boolean getCycleReport() {
        return cycleReport;
    }

    public void setCycleReport(Boolean cycleReport) {
        this.cycleReport = cycleReport;
    }

    @Parameter(key = "sync-report")
    public Boolean getSyncReport() {
        return syncReport;
    }

    public void setSyncReport(Boolean syncReport) {
        this.syncReport = syncReport;
    }

    @Override
    @Parameter(excluded = true)
    public String getPrefix() {
        return StringUtils.isNotEmpty(prefix) ? prefix : (CommonConstants.RPC + "." + PREFIX_TAG);
    }

    @Override
    @Parameter(excluded = true)
    public boolean isValid() {
        return StringUtils.isNotEmpty(address);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getCluster() {
        return cluster;
    }

    public void setCluster(Boolean cluster) {
        this.cluster = cluster;
    }
}
