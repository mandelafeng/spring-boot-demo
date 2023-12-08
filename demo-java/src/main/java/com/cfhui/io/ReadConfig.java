package com.cfhui.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/11/30 上午 10:55
 */
public class ReadConfig {
    static {
        InputStream resource = ReadConfig.class.getClassLoader().getResourceAsStream("readconf.yml");
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(resource, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(properties);
    }

    public static void main(String[] args) {
        System.out.println("args = " + args);
    }
}
