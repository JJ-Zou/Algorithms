package com.zjj.dubbo.common.constants;

import java.util.regex.Pattern;

public interface CommonConstants {
    String RPC = "rpc";
    String RPC_PROTOCOL = "rpc";
    String PROVIDER = "provider";
    String CONSUMER = "consumer";
    String APPLICATION_KEY = "application";
    String DEFAULT_RPC_PROPERTIES = "rpc.properties";
    String PATH_KEY = "path";
    String PROTOCOL_KEY = "protocol";
    String ZOOKEEPER_PROTOCOL = "zookeeper";
    String MONITOR_KEY = "monitor";
    String CLUSTER_KEY = "cluster";
    String USERNAME_KEY = "username";
    String PASSWORD_KEY = "password";
    String HOST_KEY = "host";
    String PORT_KEY = "port";
    String RPC_IP_TO_BIND = "RPC_IP_TO_BIND";
    String SHUTDOWN_WAIT_KEY = "rpc.com.zjj.service.shutdown.wait";
    String METADATA_KEY = "metadata-type";

    String CONFIG_CONFIGFILE_KEY = "config-file";
    String CONFIG_ENABLE_KEY = "highest-priority";
    String CONFIG_APP_CONFIGFILE_KEY = "app-config-file";

    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");
    String PROPERTIES_CHAR_SEPARATOR = "-";
    String METRICS_PORT = "metrics.port";
    String METRICS_PROTOCOL = "metrics.protocol";
    String DEFAULT_ROOT = "rpc";
    String PATH_SEPARATOR = "/";
    String ANYHOST_KEY = "anyhost";
    String ANYHOST_VALUE = "0.0.0.0";


    String INTERFACE_KEY = "interface";
    String METHODS_KEY = "methods";
    String GROUP_KEY = "group";
    String CLASSIFIER_KEY = "classifier";
    String VERSION_KEY = "version";
    String EMPTY_PROTOCOL = "empty";
    String ACCEPTS_KEY = "accepts";

    String ANY_VALUE = "*";
    String REMOVE_VALUE_PREFIX = "-";
    String ENABLED_KEY = "enabled";
    String DEFAULT_KEY_PREFIX = "default.";
    String FILE_KEY = "file";
    String TIMEOUT_KEY = "timeout";


    String DEVELOPMENT_ENVIRONMENT = "develop";
    String TEST_ENVIRONMENT = "test";
    String PRODUCTION_ENVIRONMENT = "product";
    String DUMP_DIRECTORY = "dump.directory";
    String QOS_ENABLE = "qos.enable";
    String QOS_HOST = "qos.host";
    String QOS_PORT = "qos.port";
    String ACCEPT_FOREIGN_IP = "qos.accept.foreign.ip";
}
