package com.xkcoding.websocket.task;

import cn.hutool.core.date.DateUtil;
import com.xkcoding.websocket.common.WebSocketConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * 服务器定时推送任务
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-14 16:04
 */
@Slf4j
@Component
public class ServerTask {
    @Autowired
    private SimpMessagingTemplate wsTemplate;

    /**
     * 按照标准时间来算，每隔 2s 执行一次
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void websocket() throws Exception {
        log.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        // 将时间戳推送到前端
        wsTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER, System.currentTimeMillis());
        log.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }
}
