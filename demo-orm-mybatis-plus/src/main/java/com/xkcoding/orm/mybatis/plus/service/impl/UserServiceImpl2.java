package com.xkcoding.orm.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkcoding.orm.mybatis.plus.entity.User;
import com.xkcoding.orm.mybatis.plus.mapper.UserMapper;
import com.xkcoding.orm.mybatis.plus.service.UserService;

import java.util.Collection;

public class UserServiceImpl2 extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }
}
