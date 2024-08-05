package com.cfhui.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/5 下午 3:05
 */
@RestController
@RequestMapping
public class CacheController {

    @Autowired
    private CacheService cacheService;
    @RequestMapping("/{key}")
    public String get(@PathVariable String key) throws InterruptedException {
        String res  = cacheService.get(key);
        return res + "-hello world";
    }
}
