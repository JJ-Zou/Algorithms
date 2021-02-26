package com.zjj.dubbo.common.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PojoUtils {
    private PojoUtils() {
    }

    public static <T> void updatePropertyIfAbsent(Supplier<T> getterMethod, Consumer<T> setterMethod, T newValue) {
        if (newValue != null && getterMethod.get() == null) {
            setterMethod.accept(newValue);
        }
    }
}
