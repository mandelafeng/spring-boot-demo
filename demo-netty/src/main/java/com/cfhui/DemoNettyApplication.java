package com.cfhui;

import com.cfhui.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@Slf4j
@SpringBootApplication
public class DemoNettyApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DemoNettyApplication.class, args);
    }


    @Autowired
    private NettyServer nettyServer;
    @Value("${socket.ip}")
    private String ip;
    @Value("${socket.port}")
    private Integer port;



    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(ip, port);
        log.info("netty服务地址：{}:{}", ip, port);
        nettyServer.start(address);
    }
}
