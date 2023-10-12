package com.cfhui.thread;

import java.util.concurrent.Executors;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/12 下午 1:19
 */
public class Pool {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(10);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(10);
    }
}
