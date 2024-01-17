package com.xkcoding.orm.mybatis.plus.service;

import com.xkcoding.orm.mybatis.plus.SpringBootDemoOrmMybatisPlusApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/28 下午 1:38
 */
@Slf4j
public class TransactionPropagationTest extends SpringBootDemoOrmMybatisPlusApplicationTests {

    @Autowired
    private TransactionPropagationServiceA service;

    @Test
    public void transaction() {
        service.main();
    }

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Test
    public void test1() {
        log.info("获取当前事务名称:" + TransactionSynchronizationManager.getCurrentTransactionName());
        // 手动提交事务
        platformTransactionManager.commit(TransactionAspectSupport.currentTransactionStatus());
    }
}
