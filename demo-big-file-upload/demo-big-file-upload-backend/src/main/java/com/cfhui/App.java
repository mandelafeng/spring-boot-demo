package com.cfhui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/2/21 下午 3:54
 */
@SpringBootApplication
@MapperScan("com.cfhui.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
