package com.cfhui.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/6 上午 10:54
 */
@Slf4j
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9998, 20);
        log.info("server begin");
        while (true) {
            Socket client = server.accept();
            log.info("accept client " + client.getPort());
            new Thread(() -> {
                InputStream in;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        String data = reader.readLine();
                        if (data != null) {
                            System.out.println(data);
                        } else {
                            client.close();
                            break;
                        }
                    }
                    log.info("client break");
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }).start();
        }
    }
}
