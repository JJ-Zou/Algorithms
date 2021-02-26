package com.zjj.dubbo.common.extension;

import com.zjj.dubbo.common.lang.Prioritized;

public interface LoadingStrategy extends Prioritized {

    String directory();

    default boolean preferExtensionClassLoader() {
        return false;
    }

    default String[] excludedPackages() {
        return null;
    }

    default boolean overridden() {
        return false;
    }
}
