package com.cfhui.observer.demo2;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/2/28 上午 10:30
 */
public class Client {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.eventManager.subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.eventManager.subscribe("save", new EmailNotificationListener("admin@example.com"));
        editor.openFile("test.txt");
        try {
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

