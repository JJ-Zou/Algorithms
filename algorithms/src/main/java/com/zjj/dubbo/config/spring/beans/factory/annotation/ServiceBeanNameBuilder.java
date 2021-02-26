package com.zjj.dubbo.config.spring.beans.factory.annotation;

import com.zjj.dubbo.common.utils.RpcAnnotationUtils;
import com.zjj.dubbo.common.utils.StringUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;

public class ServiceBeanNameBuilder {
    private static final String SEPARATOR = ":";
    private final String interfaceClassName;
    private final Environment environment;

    private String version;

    private String group;

    public ServiceBeanNameBuilder(Class<?> interfaceClass, Environment environment) {
        this(interfaceClass.getName(), environment);
    }

    public ServiceBeanNameBuilder(String interfaceClassName, Environment environment) {
        this.interfaceClassName = interfaceClassName;
        this.environment = environment;
    }

    private ServiceBeanNameBuilder(AnnotationAttributes attributes, Class<?> defaultInterfaceClass, Environment environment) {
        this(RpcAnnotationUtils.resolveInterfaceName(attributes, defaultInterfaceClass), environment);
        this.group(attributes.getString("group"));
        this.version(attributes.getString("version"));
    }

    public ServiceBeanNameBuilder group(String group) {
        this.group = group;
        return this;
    }

    public ServiceBeanNameBuilder version(String version) {
        this.version = version;
        return this;
    }

    public static ServiceBeanNameBuilder create(Class<?> defaultInterfaceClass,
                                                Environment environment) {
        return new ServiceBeanNameBuilder(defaultInterfaceClass, environment);
    }

    public static ServiceBeanNameBuilder create(AnnotationAttributes attributes,
                                                Class<?> defaultInterfaceClass,
                                                Environment environment) {
        return new ServiceBeanNameBuilder(attributes, defaultInterfaceClass, environment);
    }


    public String build() {
        StringBuilder builder = new StringBuilder("ServiceBean");
        append(builder, interfaceClassName);
        append(builder, version);
        append(builder, group);
        return environment.resolvePlaceholders(builder.toString());
    }

    private static void append(StringBuilder builder, String value) {
        if (StringUtils.hasText(value)) {
            builder.append(SEPARATOR).append(value);
        }
    }
}
