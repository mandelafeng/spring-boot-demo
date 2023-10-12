package com.cfhui.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 5:14
 */
public class ThreadA extends Thread{
    private Exchanger<String> exchanger;

    public ThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        System.out.println("线程A欲传递值'礼物A'给线程B，并等待线程B的值...");
        try {
            System.out.println("在线程A种得到线程B的值=" + exchanger.exchange("礼物A"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
