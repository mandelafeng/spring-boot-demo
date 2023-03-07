package com.cfhui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/7 下午 5:31
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootI18nApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootI18nApplication.class, args);
    }
}
