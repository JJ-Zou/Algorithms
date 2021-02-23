package com.zjj.concurrencyInPractice.sharedObject.publishAndEscape;

/**
 * this引用逸出
 * 构造函数执行期间this逸出
 * 不要在构造函数中调用可覆盖的方法(非private或final方法)
 */
public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething();
            }
        });
    }

    private void doSomething() {
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
