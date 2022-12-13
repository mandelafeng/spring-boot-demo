package com.cfhui.controller;

import com.cfhui.model.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2022/12/13 10:12
 */
@RestController
@RequestMapping("/valid")
public class ValidController {
    @GetMapping
    public User singleParamValid() {
        return User.builder().age(1).build();
    }
//    @PostMapping
//    public User test(User user) {
//        return new User();
//    }
}
