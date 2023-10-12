package com.cfhui.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 5:14
 */
public class ThreadB extends Thread{
    private Exchanger<String> exchanger;

    public ThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        System.out.println("线程B欲传递值'礼物B'给线程A，并等待线程A的值...");
        try {
            System.out.println("在线程B种得到线程A的值=" + exchanger.exchange("礼物B"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
