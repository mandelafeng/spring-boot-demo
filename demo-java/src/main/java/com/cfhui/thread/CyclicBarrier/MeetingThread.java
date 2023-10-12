package com.cfhui.thread.CyclicBarrier;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 4:49
 */
public class MeetingThread extends Thread {
    @Override
    public void run() {
        System.out.println("好了，人都到了，开始开会......");
    }
}
