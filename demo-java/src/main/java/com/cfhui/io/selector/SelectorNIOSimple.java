package com.cfhui.io.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/6 下午 1:11
 */
@Slf4j
public class SelectorNIOSimple {
    private Selector selector = null;
    int port = 9998;

    public static void main(String[] args) {
        SelectorNIOSimple service = new SelectorNIOSimple();
        service.start();
    }

    public void start() {
        initServer();
        while (true) {
            try {
                Set<SelectionKey> keys = selector.keys();
                while (selector.select() > 0) {
                    // 返回的待处理的文件描述符集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 使用后需移除，否则会被一直处理
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else {
                            readHandler(key);
                        }
                    }
                }

            } catch (IOException e) {
                log.error(e.toString());
            }
        }
    }


    private void initServer() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    private void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept();
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            client.register(selector, SelectionKey.OP_READ, buffer);
            log.info("client connected: " + client.getRemoteAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

}
