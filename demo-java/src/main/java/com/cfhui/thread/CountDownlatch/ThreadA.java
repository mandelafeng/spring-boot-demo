package com.cfhui.thread.CountDownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 4:23
 */
public class ThreadA extends Thread{
    private CountDownLatch downLatch;

    public ThreadA(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        System.out.println("A");
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("C");
    }
}
