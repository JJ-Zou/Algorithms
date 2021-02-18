package com.zjj.dubbo.rpc.common.constants;

import java.util.regex.Pattern;

public interface CommonConstants {

    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

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

}
