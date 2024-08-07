package com.cfhui;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author cfhui
 * @Date ${DATE} ${TIME}
 */
@SpringBootApplication
@EnableCaching
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }
}
