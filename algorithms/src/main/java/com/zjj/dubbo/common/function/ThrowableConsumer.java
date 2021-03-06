package com.zjj.dubbo.common.function;

@FunctionalInterface
public interface ThrowableConsumer<T> {
    static <T> void execute(T t, ThrowableConsumer<T> consumer) {
        consumer.execute(t);
    }

    void accept(T t) throws Throwable;

    default void execute(T t) throws RuntimeException {
        try {
            accept(t);
        } catch (Throwable e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
