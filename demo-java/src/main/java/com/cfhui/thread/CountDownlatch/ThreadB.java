package com.cfhui.thread.CountDownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 4:26
 */
public class ThreadB extends Thread{
    private CountDownLatch countDownLatch;

    public ThreadB(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("B");
        countDownLatch.countDown();
    }
}
