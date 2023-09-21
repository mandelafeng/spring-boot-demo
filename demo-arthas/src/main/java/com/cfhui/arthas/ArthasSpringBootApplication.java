package com.cfhui.arthas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/9/20 上午 10:30
 */
@SpringBootApplication
public class ArthasSpringBootApplication {
    public static void main(String[] args) {
        System.out.println("success");
        SpringApplication.run(ArthasSpringBootApplication.class, args);
    }
}
