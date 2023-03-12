package com.cfhui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cfhui.entity.User;
import com.cfhui.mapper.UserMapper;
import com.cfhui.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DemoServiceImpl
 * @Description TODO
 * @Author cfhui
 * @Date 2023/3/12 14:47
 */
@Service
public class DemoServiceImpl implements IDemoService {

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

    public boolean saveUser(User user) {
        //调用userMapper的insert方法插入用户数据
        int count = userMapper.insert(user);
        //如果插入成功，返回true，否则返回false
        return count > 0;
    }
}
