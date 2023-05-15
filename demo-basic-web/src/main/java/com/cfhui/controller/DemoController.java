package com.cfhui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/5/15 下午 1:46
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @GetMapping
    public String demo() {
        return "hello cfhui";
    }
}
