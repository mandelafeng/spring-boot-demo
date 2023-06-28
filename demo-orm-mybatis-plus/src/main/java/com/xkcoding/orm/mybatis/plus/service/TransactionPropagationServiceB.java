package com.xkcoding.orm.mybatis.plus.service;

import com.xkcoding.orm.mybatis.plus.entity.User;
import com.xkcoding.orm.mybatis.plus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/28 下午 2:19
 */
@Service
public class TransactionPropagationServiceB {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.NEVER)
    public void transB2() {
        User user = new User();
        user.setName("trans2");
        user.setPassword("123");
        user.setSalt("343");
        user.setEmail(System.currentTimeMillis()+"");
        user.setStatus(1);
        user.setPhoneNumber(System.currentTimeMillis()+"");
        userMapper.insert(user);
        System.out.println("324234324324");
        //transB3();
    }

    public void transB3() {
        int i = 1/0;
        User user = new User();
        user.setName("trans3");
        user.setPassword("123");
        user.setSalt("343");
        user.setEmail(System.currentTimeMillis()+"");
        user.setStatus(1);
        user.setPhoneNumber(System.currentTimeMillis()+"");
        userMapper.insert(user);
    }
}
