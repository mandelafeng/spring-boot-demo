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
public class TransactionPropagationService {
    @Autowired
    private UserMapper userMapper;
    //@Transactional(propagation = Propagation.REQUIRED)
    public void main() {
        trans1();
        trans2();
    }
    public void trans1() {
        User user = new User();
        user.setName("trans1");
        user.setPassword("123");
        user.setSalt("343");
        user.setEmail(System.currentTimeMillis()+"");
        user.setStatus(1);
        user.setPhoneNumber(System.currentTimeMillis()+"");
        userMapper.insert(user);
    }
    @Transactional(propagation = Propagation.NESTED)
    public void trans2() {
        User user = new User();
        user.setName("trans2");
        user.setPassword("123");
        user.setSalt("343");
        user.setEmail(System.currentTimeMillis()+"");
        user.setStatus(1);
        user.setPhoneNumber(System.currentTimeMillis()+"");
        userMapper.insert(user);
        System.out.println("324234324324");
        int i = 1/0;
        trans3();
    }

    public void trans3() {
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
