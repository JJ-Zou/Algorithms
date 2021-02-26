package com.zjj.dubbo.config.spring.context.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RpcComponentScanRegistrar.class)
public @interface RpcComponentScan {
    /**
     * basePackages的别名
     *
     * @return basePackages
     */
    String[] value() default {};

    /**
     * 被扫描的包名
     *
     * @return basePackages
     */
    String[] basePackages() default {};

    /**
     * 为basePackages指定类型
     *
     * @return basePackages
     */
    Class<?>[] basePackageClasses() default {};
}
