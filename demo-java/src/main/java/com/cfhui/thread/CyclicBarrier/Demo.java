package com.cfhui.thread.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 4:39
 */
public class Demo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new MeetingThread());
        new PersonThread(cyclicBarrier).start();
        new PersonThread(cyclicBarrier).start();
        new PersonThread(cyclicBarrier).start();
        new PersonThread(cyclicBarrier).start();
        new PersonThread(cyclicBarrier).start();
    }
}
