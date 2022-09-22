package com.chunfenghui.demospringsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @PreAuthorize("hasRole('HELLO')")
    @GetMapping("/hello")
    public String hello() {

        return "Hello, Security!";
    }
}

