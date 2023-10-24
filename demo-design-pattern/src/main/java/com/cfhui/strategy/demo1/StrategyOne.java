package com.cfhui.strategy.demo1;

import org.springframework.stereotype.Component;

@Component("one")
public class StrategyOne implements Strategy{

    @Override
    public String execute() {
        System.out.println("one");
        return "one";
    }
}

