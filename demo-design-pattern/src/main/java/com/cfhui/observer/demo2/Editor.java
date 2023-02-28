package com.cfhui.observer.demo2;

import java.io.File;

/**
 * [具体发布者]
 * @author cfhui
 * @version V1
 * @date 2023/2/28 上午 10:19
 */
public class Editor {
    public EventManager eventManager;

    private File file;

    public Editor() {
        this.eventManager = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        eventManager.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            eventManager.notify("save", file);
        } else {
            throw new Exception("Please open a file first");
        }
    }
}
