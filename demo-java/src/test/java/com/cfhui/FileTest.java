package com.cfhui;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName FileTest
 * @Description TODO
 * @Author cfhui
 * @Date 2022/12/11 9:05
 */
@Slf4j
public class FileTest {

    @Test
    public void getCurrentDirPath() {
        log.info(System.getProperty("user.dir"));
    }
}
