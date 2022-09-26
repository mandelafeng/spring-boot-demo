package com.cfhui.controller;

import com.cfhui.model.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/restful")
public class RestfulController {
    @GetMapping
    public ResponseBean doGet() {
        log.info("This is get test!");
        return ResponseBean.success("This is get test!");
    }
}
