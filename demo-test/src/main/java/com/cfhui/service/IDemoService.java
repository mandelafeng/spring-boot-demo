package com.cfhui.service;

import com.cfhui.entity.User;

/**
 * @ClassName DemoService
 * @Description TODO
 * @Author cfhui
 * @Date 2023/3/12 14:45
 */
public interface IDemoService {

    String getMsg();

    User getUserByName(String userName);

}
