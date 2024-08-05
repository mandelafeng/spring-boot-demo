package com.cfhui.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/5 下午 2:57
 */
@Service
@Slf4j
public class CacheService {
    @Cacheable(value = "cache")
    public String get(String key) throws InterruptedException {
        log.info("执行查询操作");
        Thread.sleep(2000);
        return key;
    }
}
