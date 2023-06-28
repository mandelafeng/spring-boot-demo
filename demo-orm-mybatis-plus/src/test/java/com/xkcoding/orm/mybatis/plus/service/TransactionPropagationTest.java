package com.xkcoding.orm.mybatis.plus.service;

import com.xkcoding.orm.mybatis.plus.SpringBootDemoOrmMybatisPlusApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/6/28 下午 1:38
 */
public class TransactionPropagationTest extends SpringBootDemoOrmMybatisPlusApplicationTests {

    @Autowired
    private TransactionPropagationServiceA service;

    @Test
    public void transaction() {
        service.main();
    }
}
