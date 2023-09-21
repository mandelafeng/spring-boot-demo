package com.cfhui.arthas.controller;

import com.cfhui.arthas.service.ArthasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/9/20 上午 10:42
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class ArthasController {
    private final ArthasService service;

    public ArthasController(ArthasService service) {
        this.service = service;
    }

    @GetMapping("/cpu")
    public String cpu() {
        service.cpu();
        return "cpu";
    }
    @GetMapping("/thread")
    public String thread() {
        service.thread();
        return "thread";
    }
    @GetMapping("/deadThread")
    public String deadThread() {
        service.deadThread();
        return "deadThread";
    }
    @GetMapping("/oom")
    public String oom() {
        service.oom();
        return "oom";
    }

    @GetMapping("/takeTime")
    public String takeTime() throws InterruptedException {
        service.takeTime();
        return "takeTime";
    }
    @GetMapping("/switchLog")
    public String switchLog() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            log.debug("this is debug");
            Thread.sleep(500);
            log.trace("this is trace");
            Thread.sleep(500);
            log.info("this is info");
            Thread.sleep(500);
            log.error("this is error");
        }
        return "switchLog";
    }
    @GetMapping("/hotUpdate")
    public String hotUpdate() throws InterruptedException {
        for (int i = 0; i < 150; i++) {
            Thread.sleep(1000);
            log.info("this is hotUpdate 1");
        }
        return "pre-hotUpdate";
    }
    @GetMapping("/timeout")
    public String timeout() throws InterruptedException {
        service.timeout();
        return "timeout";
    }

}
