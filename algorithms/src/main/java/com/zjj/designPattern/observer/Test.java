package com.zjj.designPattern.observer;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.getEventManager().subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.getEventManager().subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
