package com.xkcoding.orm.mybatis.plus.service;

import com.xkcoding.orm.mybatis.plus.SpringBootDemoOrmMybatisPlusApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void transaction() {
        service.main();
    }

    @Test
    public void test1() {
        log.info("获取当前事务名称:" + TransactionSynchronizationManager.getCurrentTransactionName());
        // 手动提交事务？
        platformTransactionManager.commit(TransactionAspectSupport.currentTransactionStatus());

        // -----------------------------------------------
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("transA");
        def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);

        TransactionStatus transaction = transactionManager.getTransaction(def);

        try {
            // 手动提交
            transactionManager.commit(transaction);
        } catch (TransactionException e) {
            // 手动回滚
            transactionManager.rollback(transaction);
        }
    }

    @Test
    public void test2() {
        // 编程式事务
      transactionTemplate.execute(new TransactionCallback<Object>() {
          @Override
          public Object doInTransaction(TransactionStatus transactionStatus) {
              // 操作A
              // 操作B
              return null;
          }
      });

    }
}
