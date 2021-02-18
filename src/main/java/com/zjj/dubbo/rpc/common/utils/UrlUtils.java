package com.zjj.dubbo.rpc.common.utils;

import com.zjj.dubbo.rpc.common.URL;

import static com.zjj.dubbo.rpc.common.constants.CommonConstants.*;
import static com.zjj.dubbo.rpc.common.constants.RegistryConstants.CATEGORY_KEY;
import static com.zjj.dubbo.rpc.common.constants.RegistryConstants.DEFAULT_CATEGORY;

public class UrlUtils {

    public static boolean isMatch(URL consumerUrl, URL providerUrl) {
        String consumerInterface = consumerUrl.getServiceInterface();
        String providerInterface = providerUrl.getServiceInterface();
        if (!(ANY_VALUE.equals(consumerInterface) || ANY_VALUE.equals(providerInterface) || StringUtils.isEquals(consumerInterface, providerInterface))) {
            return false;
        }
        if (!isMatchCategory(providerUrl.getParameter(CATEGORY_KEY, DEFAULT_CATEGORY), consumerUrl.getParameter(CATEGORY_KEY, DEFAULT_CATEGORY))) {
            return false;
        }
        if (!providerUrl.getParameter(ENABLED_KEY, true) && !ANY_VALUE.equals(consumerUrl.getParameter(ENABLED_KEY))) {
            return false;
        }
        String consumerGroup = consumerUrl.getParameter(GROUP_KEY);
        String consumerVersion = consumerUrl.getParameter(VERSION_KEY);
        String consumerClassifier = consumerUrl.getParameter(CLASSIFIER_KEY, ANY_VALUE);
        String providerGroup = providerUrl.getParameter(GROUP_KEY);
        String providerVersion = providerUrl.getParameter(VERSION_KEY);
        String providerClassifier = providerUrl.getParameter(CLASSIFIER_KEY, ANY_VALUE);
        return (ANY_VALUE.equals(consumerGroup)) || StringUtils.isEquals(consumerGroup, providerGroup) || StringUtils.isContains(consumerGroup, providerGroup)
                && (ANY_VALUE.equals(consumerVersion) || StringUtils.isEquals(consumerVersion, providerVersion))
                && (consumerClassifier == null || ANY_VALUE.equals(consumerClassifier) || StringUtils.isEquals(consumerClassifier, providerClassifier));

    }

    public static boolean isMatchCategory(String category, String categories) {
        if (StringUtils.isEmpty(categories)) {
            return DEFAULT_CATEGORY.equals(category);
        }
        if (categories.contains(ANY_VALUE)) {
            return true;
        }
        if (categories.contains(REMOVE_VALUE_PREFIX)) {
            return !categories.contains(REMOVE_VALUE_PREFIX + category);
        }
        return categories.contains(category);
    }
}
