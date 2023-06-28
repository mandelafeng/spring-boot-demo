package com.xkcoding.orm.mybatis.plus.service;

import com.xkcoding.orm.mybatis.plus.entity.User;
import com.xkcoding.orm.mybatis.plus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/28 下午 2:19
 */
@Service
public class TransactionPropagationServiceA {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionPropagationServiceB b;
    @Transactional(propagation = Propagation.REQUIRED)
    public void main() {
        trans1();
        b.transB2();
        //int i = 1/0;
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
    /**
     * [ 编程式事务-解决大事务的问题 ]
     * @author cfhui
     * @since V1
     * @date 2023/6/28 下午 4:29
     */
    @Autowired
    private TransactionTemplate transactionTemplate;
    public void transA2() {
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.execute((TransactionCallback<Object>) status -> {
            transA2();
            b.transB3();
            return Boolean.TRUE;
        });
    }

}
