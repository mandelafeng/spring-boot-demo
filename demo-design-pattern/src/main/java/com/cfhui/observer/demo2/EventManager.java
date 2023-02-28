package com.cfhui.observer.demo2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [基础发布者]
 * @author cfhui
 * @version V1
 * @date 2023/2/28 上午 10:08
 */
public class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }
    /**
     * [订阅]
     * @param eventType 事件类型
     * @param listener 订阅者列表
     * @return void
     * @author cfhui
     * @since V1
     * @date 2023/2/28 上午 10:13
     */
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * [取消订阅]
     * @param eventType 事件类型
     * @param listener 订阅者列表
     * @return void
     * @author cfhui
     * @since V1
     * @date 2023/2/28 上午 10:13
     */
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }
    /**
     * [发布订阅通知]
     * @param eventType 事件类型
     * @param file 事件参数
     * @return void
     * @author cfhui
     * @since V1
     * @date 2023/2/28 上午 10:16
     */
    public void notify(String eventType, File file) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(eventType, file);
        }
    }

}
