package com.cfhui.thread.CountDownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 4:27
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new ThreadA(countDownLatch).start();
        new ThreadB(countDownLatch).start();
        System.out.println("D");
    }
}
