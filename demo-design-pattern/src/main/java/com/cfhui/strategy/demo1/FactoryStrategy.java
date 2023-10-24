package com.cfhui.strategy.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FactoryStrategy {

    @Autowired
    Map<String,Strategy> strategys = new ConcurrentHashMap<>(3);

    public Strategy getStragegy(String component) throws Exception{
        Strategy strategy = strategys.get(component);
        if(strategy == null){
            throw new RuntimeException("no strategy defined");
        }
        return strategy;
    }

}
