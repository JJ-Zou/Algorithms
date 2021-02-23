package com.zjj.designPattern.observer;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventManager {
    Map<String, Set<MyEventListener>> listeners = new HashMap<>();

    public EventManager(String... eventTypes) {
        for (String eventType : eventTypes) {
            listeners.put(eventType, new HashSet<>());
        }
    }

    public void subscribe(String eventType, MyEventListener listener) {
        Set<MyEventListener> eventListeners = listeners.get(eventType);
        eventListeners.add(listener);
    }

    public void unsubscribe(String eventType, MyEventListener listener) {
        Set<MyEventListener> eventListeners = listeners.get(eventType);
        eventListeners.remove(listener);
    }

    public void notify(String eventType, File file) {
        Set<MyEventListener> eventListeners = listeners.get(eventType);
        for (MyEventListener listener : eventListeners) {
            listener.update(eventType, file);
        }
    }
}
