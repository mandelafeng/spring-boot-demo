package com.cfhui;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/8/2 上午 9:31
 */
public class ThreadTest {
    @Test
    public void test1() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,
            20,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
        );
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        // 添加到任务集合
        List<Callable<Integer>> taskList = new ArrayList<>();
        for (Integer i : list) {
            taskList.add(() -> i * 2);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (Integer i : list) {
            executor.submit(() -> {
                System.out.println(i);
                System.out.println(Thread.currentThread().getName());
            });
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
        // List<Future<Integer>> futures = executor.invokeAll(taskList);
//        for (Future<Integer> future : futures) {
//            if (future.isDone()) {
//                System.out.println(future.get());
//            }
//        }
//        for (Integer i : list) {
//            Callable<Integer> callable = () -> i * 2;
//            Future<Integer> submit = executor.submit(callable);
//            System.out.println(submit.get());
//        }

    }

    @Test
    public void test2() {
        // 固定线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
    }

    @Test
    @DisplayName("Semaphore")
    public void test3() {
        Semaphore semaphore = new Semaphore(3);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,
            20,
            5000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100)
        );
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            fixedThreadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(finalI + "===========" + semaphore.availablePermits() + "==========" + semaphore.getQueueLength() + "========" + Thread.currentThread().getName());
                    Random random = new Random();
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
//            new Thread(() -> {
//                try {
//                    semaphore.acquire();
//                    System.out.println(finalI + "===========" + semaphore.availablePermits() + "==========" + semaphore.getQueueLength() + "========" + Thread.currentThread().getName());
//                    Random random = new Random();
//                    Thread.sleep(random.nextInt(1000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            }).start();
        }
        fixedThreadPool.shutdown();
    }

}
