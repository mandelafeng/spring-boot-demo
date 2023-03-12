package com.cfhui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cfhui.mapper")
public class SpringBootDemoTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoTest.class, args);
    }
}
