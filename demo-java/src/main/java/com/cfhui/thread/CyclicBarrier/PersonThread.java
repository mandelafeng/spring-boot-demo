package com.cfhui.thread.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 4:45
 */
public class PersonThread extends Thread{
    private CyclicBarrier cyclicBarrier;

    public PersonThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int)Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + " 到了！");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
