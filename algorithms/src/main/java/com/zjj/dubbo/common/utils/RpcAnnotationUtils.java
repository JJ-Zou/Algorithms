package com.zjj.dubbo.common.utils;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ClassUtils;

public class RpcAnnotationUtils {
    private RpcAnnotationUtils() {
    }

    public static Class<?> resolveServiceInterfaceClass(AnnotationAttributes attributes, Class<?> defaultInterfaceClass)
            throws IllegalArgumentException {
        ClassLoader classLoader = defaultInterfaceClass != null ? defaultInterfaceClass.getClassLoader() :
                Thread.currentThread().getContextClassLoader();
        Class<?> interfaceClass = attributes.getClass("interfaceClass");
        if (void.class.equals(interfaceClass)) {
            interfaceClass = null;
            String interfaceName = attributes.getString("interfaceName");
            if (StringUtils.hasText(interfaceName) && ClassUtils.isPresent(interfaceName, classLoader)) {
                interfaceClass = ClassUtils.resolveClassName(interfaceName, classLoader);
            }
        }
        if (interfaceClass == null && defaultInterfaceClass != null) {
            Class<?>[] allInterfacesForClass = ClassUtils.getAllInterfacesForClass(defaultInterfaceClass);
            if (allInterfacesForClass.length > 0) {
                interfaceClass = allInterfacesForClass[0];
            }
        }
        return interfaceClass;
    }

    public static String resolveInterfaceName(AnnotationAttributes attributes, Class<?> defaultInterfaceClass) {
        boolean generic = attributes.getBoolean("generic");
        if (generic) {
            String interfaceClassName = attributes.getString("interfaceName");
            return interfaceClassName;
        }
        return resolveServiceInterfaceClass(attributes, defaultInterfaceClass).getName();
    }
}
