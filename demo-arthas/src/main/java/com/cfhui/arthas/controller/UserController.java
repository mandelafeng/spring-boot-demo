package com.cfhui.arthas.controller;

import com.cfhui.arthas.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/user")
    public HashMap<String, Object> getUser(Integer uid) throws Exception {
        // 模拟用户查询
        userService.get(uid);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("name", "name" + uid);
        return hashMap;
    }
}
