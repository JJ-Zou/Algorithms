package com.zjj.dubbo.common.function;

@FunctionalInterface
public interface ThrowableFunction<T, R> {
    static <T, R> R execute(T t, ThrowableFunction<T, R> function) {
        return function.execute(t);
    }

    R apply(T t) throws Throwable;

    default R execute(T t) throws RuntimeException {
        R result = null;
        try {
            result = apply(t);
        } catch (Throwable e) {
            throw new RuntimeException(e.getCause());
        }
        return result;
    }
}
