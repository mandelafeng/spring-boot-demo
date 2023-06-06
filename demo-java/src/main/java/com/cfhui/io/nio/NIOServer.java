package com.cfhui.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/6 上午 11:14
 */
@Slf4j
public class NIOServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        // 服务端开启监听
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9998));
        // 设置操作系统 级别非阻塞 NONBLOCKING
        serverSocketChannel.configureBlocking(false);
        while (true) {
            // 接收客户端的连接
            Thread.sleep(500);
            /**
             * accept 调用了内核
             * 在设置configureBlocking(false) 及非阻塞的情况下
             * 若有客户端连进来， 直接返回客户端
             * 若无客户端连接，则返回null
             * 设置成NONBLOCKING后，代码不阻塞，线程不挂起，继续往下执行
             */
            SocketChannel client = serverSocketChannel.accept();
            if (client == null) {
                log.warn("client is null");
            } else {
                /**
                 * 重点，设置client读写数据时非阻塞
                 */
                client.configureBlocking(false);
                int port = client.socket().getPort();
                log.info("client port: {}", port);
                clients.add(client);
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            // 遍历所有客户端，不需要多线程
            // 这里有一个问题，无论是否有读写事件，都需要空遍历所有的客户端连接，产生大量的系统调用，浪费大量CPU资源
            for (SocketChannel c : clients) {
                // 不阻塞
                int num = c.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);
                    String b = new String(aaa);
                    log.info(c.socket().getPort() + ":" + b);
                    buffer.clear();
                }
            }
        }
    }
}
