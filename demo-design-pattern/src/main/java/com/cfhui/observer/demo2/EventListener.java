package com.cfhui.observer.demo2;

import java.io.File;
/**
 * [通用观察者接口]
 * @author cfhui
 * @version  V1
 * @date 2023/2/28 上午 10:07
 */
public interface EventListener {
    void update(String evenType, File file);
}
