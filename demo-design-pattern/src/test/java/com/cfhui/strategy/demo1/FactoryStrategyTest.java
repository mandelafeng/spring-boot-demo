package com.cfhui.strategy.demo1;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FactoryStrategyTest {
    @Autowired
    private FactoryStrategy factoryStrategy;

    @Test
    void test() throws Exception {
        Strategy strategyOne = factoryStrategy.getStragegy("one");
        strategyOne.execute();
    }
}
