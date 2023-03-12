package com.cfhui.controller;

import com.cfhui.entity.User;
import com.cfhui.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author cfhui
 * @Date 2023/3/12 14:42
 */
@RestController
@RequestMapping
public class DemoController {
    @Autowired
    private IDemoService demoService;
    @GetMapping("test1")
    public String test1() {
        return demoService.getMsg();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String userName) {
        return demoService.getUserByName(userName);
    }

}
