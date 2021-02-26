package com.zjj.dubbo.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassUtils {
    public static final Set<Class<?>> SIMPLE_TYPES = Stream.of(
            Void.class,
            Boolean.class,
            Character.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            String.class,
            BigDecimal.class,
            BigInteger.class,
            Date.class,
            Object.class
    ).collect(Collectors.toSet());

    private ClassUtils() {
    }

    public static boolean isPrimitive(Class<?> type) {
        return type != null && (type.isPrimitive() || isSimpleType(type));
    }

    public static boolean isSimpleType(Class<?> type) {
        return SIMPLE_TYPES.contains(type);
    }
}
