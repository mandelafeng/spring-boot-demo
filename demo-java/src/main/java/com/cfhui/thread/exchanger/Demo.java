package com.cfhui.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 5:17
 */
public class Demo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        ThreadB b = new ThreadB(exchanger);
        a.start();
        b.start();
    }
}
