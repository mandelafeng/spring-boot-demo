package com.xkcoding.orm.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xkcoding.orm.mybatis.plus.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserJoinMapperTest {

    @Autowired
    private UserJoinMapper userJoinMapper;
    @Test
    public void testJoin() {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.groupBy(User::getEmail);

    }

}
