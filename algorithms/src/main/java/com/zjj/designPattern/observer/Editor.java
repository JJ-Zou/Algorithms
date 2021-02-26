package com.zjj.designPattern.observer;

import java.io.File;

public class Editor {
    private final EventManager eventManager;
    private File file;

    public Editor() {
        this.eventManager = new EventManager("open", "save");
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        eventManager.notify("open", file);
    }

    public void saveFile() {
        if (this.file != null) {
            eventManager.notify("save", file);
        } else {
            throw new RuntimeException("Please open a file first.");
        }
    }

}
