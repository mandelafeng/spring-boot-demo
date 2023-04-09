package com.cfhui;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JavaTest
 * @Description TODO
 * @Author cfhui
 * @Date 2023/4/5 11:32
 */
public class JavaTest {
    @Test
    public void test1() {
        int x = 2;
        change(x);
        System.out.println(x);
    }

    private void change(int x) {
        x = 100;
    }

    @Test
    public void test2() {
        Integer integer = 1;
        List<String> strings = new ArrayList<>();

    }
}
