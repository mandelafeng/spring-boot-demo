package com.cfhui.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IDemoServiceTest {

    @Autowired
    private IDemoService demoService;

    @Test
    void getMsg() {
        Assertions.assertSame("world", demoService.getMsg());
    }
}
