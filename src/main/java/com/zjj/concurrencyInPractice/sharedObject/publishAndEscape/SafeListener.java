package com.zjj.concurrencyInPractice.sharedObject.publishAndEscape;

/**
 * 使用工厂方法防止this逸出
 */
public class SafeListener {
    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething();
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safeListener = new SafeListener();
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    public void doSomething() {
    }

    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}
