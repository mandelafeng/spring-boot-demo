package com.cfhui;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/28 上午 10:01
 */
@Slf4j
public class ShellTest {
    /**
     *
     * 主进程中调用Runtime.exec会创建一个子进程，用于执行脚本。子进程创建后会和主进程分别独立运行。
     * 创建的子进程没有自己的终端或控制台。它的所有标准 io（即 stdin、stdout 和 stderr）操作都将通过三个流
     * (getOutputStream()、getInputStream() 和 getErrorStream()) 重定向到父进程。父进程使用这些流来提供到子进程的输入和获得从子进程的输出。
     * 这时候子进程不断向主进程发生数据，而主进程调用Process.waitfor后已挂起。当前子进程和主进程之间的缓冲区塞满后，子进程不能继续写数据，然后也会挂起。
     * 这样子进程等待主进程读取数据，主进程等待子进程结束，两个进程相互等待，最终导致死锁。
     */
    @Test
    public void test1() {
        // 执行命令，返回一个子进程对象
        Process process = null;
        StringBuilder sb = new StringBuilder();
        try {
            process = Runtime.getRuntime().exec("java -version");
            // 获取执行结果 标准输出 错误输出 （PS: 子进程的输出就是主进程的输入）
            StreamGobbler output = new StreamGobbler(process.getInputStream(), "OUTPUT");
            StreamGobbler error = new StreamGobbler(process.getErrorStream(), "ERROR");
            Thread thread1 = new Thread(output);
            Thread thread2 = new Thread(error);
            thread1.start();
            thread2.start();
            // 方法阻塞 成功会返回0
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private String type;

        StreamGobbler(InputStream inputStream, String type) {
            this.inputStream = inputStream;
            this.type = type;
        }

        @Override
        public void run() {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(type + ">" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
