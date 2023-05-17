package com.cfhui.controller;

import com.cfhui.mystarter.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/starter")
public class StarterController {

    @Autowired
    private Person person;
    @GetMapping
    public Person demo() {
        return person;
    }
}
