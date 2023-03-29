package com.cfhui;

import org.junit.Test;

import java.io.IOException;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/28 上午 10:01
 */
public class ShellTest {
    @Test
    public void test1() throws IOException {
        Process exec = Runtime.getRuntime().exec("java -version");
        System.out.print(exec);


    }
}
