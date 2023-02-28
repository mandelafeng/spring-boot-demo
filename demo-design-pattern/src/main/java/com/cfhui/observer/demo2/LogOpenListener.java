package com.cfhui.observer.demo2;

import java.io.File;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/2/28 上午 10:28
 */
public class LogOpenListener implements EventListener{
    private File log;

    public LogOpenListener(String filePath) {
        this.log = new File(filePath);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log "
            + log
            + ": Someone has performed "
            + eventType + " operation with the following file: "
            + file.getName());
    }
}

