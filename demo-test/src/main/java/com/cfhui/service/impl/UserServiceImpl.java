package com.cfhui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cfhui.entity.User;
import com.cfhui.mapper.UserMapper;
import com.cfhui.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DemoServiceImpl
 * @Description TODO
 * @Author cfhui
 * @Date 2023/3/12 14:47
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getMsg() {
        return "world";
    }

    @Override
    public User getUserByName(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user = userMapper.selectUserByUserName(userName);
        return user;
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean update(User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", user.getUserName());
        wrapper.set("email", user.getEmail());
        return userMapper.update(null, wrapper) > 0;
    }

    @Override
    public List<User> list() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEnable, "0");
        return userMapper.selectList(wrapper);
    }
}
