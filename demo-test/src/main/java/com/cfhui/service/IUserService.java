package com.cfhui.service;

import com.cfhui.entity.User;

import java.util.List;

/**
 * @ClassName DemoService
 * @Description TODO
 * @Author cfhui
 * @Date 2023/3/12 14:45
 */
public interface IUserService {

    String getMsg();

    User getUserByName(String userName);

    boolean saveUser(User user);

    boolean update(User user);

    List<User> list();

}
