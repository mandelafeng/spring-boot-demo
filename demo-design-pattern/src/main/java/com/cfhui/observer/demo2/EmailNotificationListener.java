package com.cfhui.observer.demo2;

import java.io.File;

/**
 * [收到通知后发送邮件]
 *
 * @author cfhui
 * @version V1
 * @date 2023/2/28 上午 10:24
 */
public class EmailNotificationListener implements EventListener {
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String evenType, File file) {
        System.out.println("Email to"
            + email
            + ": Someone has performed"
            + " operation with the following file: "
            + file.getName());
    }
}
