package com.zjj.dubbo.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectUtils {
    public static Set<ParameterizedType> findParameterizedTypes(Class<?> sourceClass) {
        List<Type> genericTypes = new LinkedList<>(Arrays.asList(sourceClass.getGenericInterfaces()));
        genericTypes.add(sourceClass.getGenericSuperclass());
        Set<ParameterizedType> parameterizedTypes = genericTypes.stream()
                .filter(type -> type instanceof ParameterizedType)
                .map(type -> ParameterizedType.class.cast(type))
                .collect(Collectors.toSet());
        if (parameterizedTypes.isEmpty()) {
            genericTypes.stream()
                    .filter(type -> type instanceof Class)
                    .map(type -> Class.class.cast(type))
                    .forEach(superClass -> parameterizedTypes.addAll(findParameterizedTypes(superClass)));
        }
        return Collections.unmodifiableSet(parameterizedTypes);
    }

    public static Class<?> getBoxedClass(Class<?> c) {
        if (c == int.class) {
            c = Integer.class;
        } else if (c == boolean.class) {
            c = Boolean.class;
        } else if (c == long.class) {
            c = Long.class;
        } else if (c == float.class) {
            c = Float.class;
        } else if (c == double.class) {
            c = Double.class;
        } else if (c == char.class) {
            c = Character.class;
        } else if (c == byte.class) {
            c = Byte.class;
        } else if (c == short.class) {
            c = Short.class;
        }
        return c;
    }

    public static boolean isPrimitives(Class<?> cls) {
        if (cls.isArray()) {
            return isPrimitive(cls.getComponentType());
        }
        return isPrimitive(cls);
    }

    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class
                || cls == Character.class || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }
}
