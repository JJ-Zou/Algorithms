package com.zjj.designPattern.observer;

import java.io.File;

public interface MyEventListener {
    void update(String eventType, File file);
}
