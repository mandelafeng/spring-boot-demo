package com.cfhui.strategy.demo1;

import org.springframework.stereotype.Component;

@Component("two")
public class StrategyTwo implements Strategy {

    @Override
    public String execute() {
        System.out.println("two");
        return "two";
    }
}
